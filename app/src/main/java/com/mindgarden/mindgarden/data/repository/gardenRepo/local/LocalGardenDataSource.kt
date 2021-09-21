package com.mindgarden.mindgarden.data.repository.gardenRepo.local

import com.mindgarden.mindgarden.data.model.entity.Garden
import com.mindgarden.mindgarden.data.repository.gardenRepo.GardenDataSource
import com.mindgarden.mindgarden.data.repository.gardenRepo.local.dao.GardenDao
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import java.util.*

class LocalGardenDataSource(private val gardenDao: GardenDao): GardenDataSource {

    override fun plantTree(garden: Garden): Completable {
        return gardenDao.insert(garden)
    }

    override fun getGarden(date: Date): Single<Garden> {
        return gardenDao.getGarden()
    }
}