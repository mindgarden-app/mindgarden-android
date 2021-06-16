package com.mindgarden.mindgarden.data.source.local.dao

import androidx.room.*
import com.mindgarden.mindgarden.data.model.Garden

@Dao
abstract class GardenDao : BaseDao<Garden> {

//    @Update
//    fun changeTree(garden: Garden): Int
}