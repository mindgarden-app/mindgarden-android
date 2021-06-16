package com.mindgarden.mindgarden.data.source.local.dao

import androidx.room.*
import com.mindgarden.mindgarden.data.model.Diary

@Dao
abstract class DiaryDao : BaseDao<Diary> {

    @Query("SELECT * From diary WHERE date=:date")
    abstract fun loadDiary(date: String): List<Diary>
}