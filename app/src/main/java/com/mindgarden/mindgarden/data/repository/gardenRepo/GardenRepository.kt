package com.mindgarden.mindgarden.data.repository.gardenRepo

import com.mindgarden.mindgarden.data.model.entity.Mind
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import java.time.LocalDateTime

interface GardenRepository {
    fun plantTree(mind: Mind): Completable
    fun getGarden(date: LocalDateTime): Single<List<Mind>>
}