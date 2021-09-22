package com.mindgarden.mindgarden.data.repository.gardenRepo.local

import com.mindgarden.mindgarden.data.model.entity.Mind
import com.mindgarden.mindgarden.data.repository.gardenRepo.GardenDataSource
import com.mindgarden.mindgarden.data.repository.gardenRepo.local.dao.GardenDao
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import java.time.LocalDateTime

class LocalGardenDataSource(private val gardenDao: GardenDao): GardenDataSource {

    override fun plantTree(mind: Mind): Completable {
        return gardenDao.insert(mind)
    }

    override fun getGarden(date: LocalDateTime): Single<List<Mind>> {
        return gardenDao.getGarden(date)
    }
}