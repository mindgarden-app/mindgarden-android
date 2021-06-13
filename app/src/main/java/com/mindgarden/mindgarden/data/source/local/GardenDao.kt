package com.mindgarden.mindgarden.data.source.local

import androidx.room.*
import com.mindgarden.mindgarden.data.model.Garden

@Dao
interface GardenDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun plantTree(garden: Garden)

//    @Update
//    fun changeTree(garden: Garden): Int
}