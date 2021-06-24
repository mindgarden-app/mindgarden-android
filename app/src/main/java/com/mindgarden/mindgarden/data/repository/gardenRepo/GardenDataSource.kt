package com.mindgarden.mindgarden.data.repository.gardenRepo

import com.mindgarden.mindgarden.data.model.entity.Garden
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import java.util.*

// local source와 remote source
interface GardenDataSource {
    fun plantTree(garden: Garden): Completable
    fun getGarden(date: Date): Single<Garden>
}