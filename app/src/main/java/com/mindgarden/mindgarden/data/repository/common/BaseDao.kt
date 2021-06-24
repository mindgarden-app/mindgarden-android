package com.mindgarden.mindgarden.data.repository.common

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update
import io.reactivex.rxjava3.core.Completable

interface BaseDao<T> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg obj: T) : Completable

    @Delete
    fun delete(vararg obj: T) : Completable

    @Update
    fun update(vararg obj: T) : Completable
}