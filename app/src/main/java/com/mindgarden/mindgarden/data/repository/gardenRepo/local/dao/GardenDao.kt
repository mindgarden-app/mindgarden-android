package com.mindgarden.mindgarden.data.repository.gardenRepo.local.dao

import androidx.room.*
import com.mindgarden.mindgarden.data.model.entity.Garden
import com.mindgarden.mindgarden.data.repository.common.BaseDao
import io.reactivex.rxjava3.core.Single

@Dao
abstract class GardenDao : BaseDao<Garden> {

    @Query("SELECT * from garden")
    abstract fun getGarden(): Single<Garden>
}