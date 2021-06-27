package com.mindgarden.mindgarden.data.source.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.mindgarden.mindgarden.data.model.Diary

@Dao
interface DiaryDao {
    @Query("SELECT * FROM diary")
    fun getAll() : LiveData<List<Diary>>

    @Insert
    fun insert(diary: Diary)

    @Delete
    fun delete(diary: Diary)
}