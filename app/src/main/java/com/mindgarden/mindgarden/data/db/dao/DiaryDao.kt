package com.mindgarden.mindgarden.data.db.dao

import android.database.sqlite.SQLiteException
import androidx.room.*
import com.mindgarden.mindgarden.data.db.entity.Diary
import com.mindgarden.mindgarden.data.db.entity.DiaryUpdate
import kotlinx.coroutines.flow.Flow

@Dao
abstract class DiaryDao : BaseDao<Diary> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertAll(diaries: List<Diary>): List<Long>

    /**
     * 일기 상세 조회
     */
    @Query("SELECT * FROM diary WHERE idx = :idx")
    @Throws(SQLiteException::class)
    abstract fun loadDiary(idx: Long): Flow<Diary>

    /**
     * 일기 목록 조회
     * date : "yyyy-MM" (달별 조회), "yyyy-MM-dd" (일별 조회)
     * isAsc : 1 - 오래된 순, 0 - 최신 순
     */
    @Query("SELECT * FROM diary WHERE date LIKE '%' || :date || '%' ORDER BY " +
            "CASE WHEN :isAsc = 1 THEN date END ASC," +
            "CASE WHEN :isAsc = 0 THEN date END DESC")
    @Throws(SQLiteException::class)
    abstract fun loadDiaryList(date: String, isAsc: Boolean): Flow<List<Diary>>

    /**
     * 일기 수정
     */
    @Update(entity = Diary::class, onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun updateDiary(obj: DiaryUpdate)

    /**
     * 일기 삭제
     */
    @Query("DELETE FROM diary WHERE idx = :idx")
    @Throws(SQLiteException::class)
    abstract suspend fun deleteDiaryByIdx(idx: Long)
}