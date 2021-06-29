package com.mindgarden.mindgarden

import android.content.Context
import android.util.Log
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.mindgarden.mindgarden.data.model.entity.Diary
import com.mindgarden.mindgarden.data.repository.database.MindGardenDatabase
import com.mindgarden.mindgarden.data.repository.diaryRepo.local.DiaryDao
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.io.IOException
import java.util.*

class DiaryDatabaseTest {
    lateinit var db: MindGardenDatabase
    lateinit var diaryDao: DiaryDao

    @Before
    fun createDB() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, MindGardenDatabase::class.java).build()
        diaryDao = db.diaryDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    fun diaryTest() {
        val date = Calendar.getInstance().time
        val diary = Diary(
            0,
            date,
            "diary test",
            1,
            "good day", null
        )
        diaryDao.insert(diary).subscribe(
            {
                Log.d("DiaryDatabaseTest", "Success insert")
            },
            {
                Log.e("DiaryDatabaseTest", "Error : $it")
            }
        )

        diaryDao.loadDiaries(date).subscribe(
            {
                Log.d("DiaryDatabaseTest", "Success load diary : ${it.size}")
            },
            {
                Log.e("DiaryDatabaseTest", "Error : $it")
            }
        )
    }
}