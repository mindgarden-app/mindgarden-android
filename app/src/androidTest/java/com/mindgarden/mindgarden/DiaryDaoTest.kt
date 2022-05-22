package com.mindgarden.mindgarden

import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import androidx.test.platform.app.InstrumentationRegistry
import com.mindgarden.mindgarden.data.db.MindGardenDatabase
import com.mindgarden.mindgarden.data.db.dao.DiaryDao
import com.mindgarden.mindgarden.data.db.entity.DiaryUpdate
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.test.*
import org.hamcrest.CoreMatchers.equalTo
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.time.LocalDateTime

@OptIn(ExperimentalCoroutinesApi::class)
class DiaryDaoTest {
    private lateinit var db: MindGardenDatabase
    private lateinit var diaryDao: DiaryDao
    private val testDispatcher = UnconfinedTestDispatcher()

    // 안드로이드 컴포넌트 관련 작업들을 모두 한 스레드에서 실행되게 함 -> 모든 작업이 동기화 됨
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun createDb() = runTest {
        Dispatchers.setMain(testDispatcher)
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        db = Room.inMemoryDatabaseBuilder(context, MindGardenDatabase::class.java).build()
        diaryDao = db.diaryDao()
        val insertResult = diaryDao.insertAll(mockDiaryList)
    }

    @After
    fun closeDb() {
        db.close()
        Dispatchers.resetMain()
    }

    // 일기 조회
    @Test
    fun testLoadDiaryList() = runTest {
        // 연간 데이터 조회 - 최신순
        val resultAll = diaryDao.loadDiaryList("2022", false).first()
        assertThat(resultAll.size, equalTo(4))

        // 월간 데이터 조회 - 최신순(31~1)
        val resultSortByDESC = diaryDao.loadDiaryList("2022-04", false).first()
        assertThat(resultSortByDESC.size, equalTo(3))
        assertThat(
            resultSortByDESC[0].date,
            equalTo(LocalDateTime.of(mockLocalDate2, mockLocalTime1))
        )

        // 월간 데이터 조회 - 오래된 순 (1~31)
        val resultSortByASC = diaryDao.loadDiaryList("2022-04", true).first()
        assertThat(resultSortByASC[0].contents, equalTo(mockDefaultDiary.contents))

        // 일간 데이터 조회
        val resultDaily = diaryDao.loadDiaryList("2022-04-04", false).first()
        assertThat(resultDaily.size, equalTo(2))
    }

    // 일기 삭제
    @Test
    fun testDeleteDiary() = runTest {
        val resultAll = diaryDao.loadDiaryList("2022", false).first()
        assertThat(resultAll.size, equalTo(4))
        val deleteIdx = resultAll[0].idx
        diaryDao.deleteDiaryByIdx(deleteIdx)

        val resultAfterDelete = diaryDao.loadDiaryList("2022", false).first()
        assertThat(resultAfterDelete.size, equalTo(3))
    }

    // 일기 수정
    @Test
    fun testUpdateDiary() = runTest {
        val oldDiary = diaryDao.loadDiaryList("2022", false)
            .map { diaryList -> diaryList[0] }
            .first()

        val newDiary = DiaryUpdate(
            idx = oldDiary.idx,
            contents = "new Diary",
            weather = 3,
            weatherText = "new weather text",
            img = null
        )
        assertThat(oldDiary.idx, equalTo(newDiary.idx))
        diaryDao.updateDiary(newDiary)

        val result = diaryDao.loadDiary(newDiary.idx).first()
        Log.d("DiaryDaoTest", "testUpdateDiary: $result")
        assertThat(result.contents, equalTo(newDiary.contents))
    }
}