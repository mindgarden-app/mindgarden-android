package com.mindgarden.mindgarden.data.db.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update

interface BaseDao<T> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(obj: T): Long

    @Delete
    suspend fun delete(vararg obj: T)

    @Update
    suspend fun update(vararg obj: T)
}