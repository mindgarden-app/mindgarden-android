package com.mindgarden.mindgarden.data.source.local

import androidx.room.*
import com.mindgarden.mindgarden.data.model.Diary

@Dao
interface DiaryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun writeDiary(diary: Diary)

    @Query("SELECT * From diary WHERE date=:date")
    fun loadDiary(date: String): List<Diary>

    @Update
    fun editDiary(diary: Diary):Int

    @Delete
    fun deleteDiary(diary: Diary):Int
}