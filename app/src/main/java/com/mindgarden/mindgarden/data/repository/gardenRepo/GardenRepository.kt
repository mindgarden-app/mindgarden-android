package com.mindgarden.mindgarden.data.repository.gardenRepo

import com.mindgarden.mindgarden.data.model.entity.Garden
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import java.util.*

// TODO("수정필요")
interface GardenRepository {
    fun plantTree(garden: Garden): Completable
    fun getGarden(date: Date): Single<Garden>
}