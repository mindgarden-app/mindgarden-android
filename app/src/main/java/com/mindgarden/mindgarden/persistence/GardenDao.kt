package com.mindgarden.mindgarden.persistence

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy

@Dao
interface GardenDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun plantTree(garden:Garden)

}