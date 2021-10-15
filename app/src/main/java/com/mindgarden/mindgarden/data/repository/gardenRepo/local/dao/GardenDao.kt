package com.mindgarden.mindgarden.data.repository.gardenRepo.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.mindgarden.mindgarden.data.model.entity.Mind
import com.mindgarden.mindgarden.data.repository.common.BaseDao
import io.reactivex.rxjava3.core.Single
import java.time.LocalDateTime

@Dao
abstract class GardenDao : BaseDao<Mind> {

    @Query("SELECT * from mind WHERE gardenDate=:date")
    abstract fun getGarden(date: LocalDateTime): Single<List<Mind>>
}