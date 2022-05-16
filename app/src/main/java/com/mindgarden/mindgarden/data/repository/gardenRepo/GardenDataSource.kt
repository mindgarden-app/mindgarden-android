package com.mindgarden.mindgarden.data.repository.gardenRepo

import com.mindgarden.mindgarden.data.db.entity.Mind
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import java.time.LocalDateTime

interface GardenDataSource {
    fun plantTree(mind: Mind): Completable
    fun getGarden(date: LocalDateTime): Single<List<Mind>>
}