package com.mindgarden.mindgarden.data.repository.gardenRepo

import com.mindgarden.mindgarden.data.model.entity.Garden
import com.mindgarden.mindgarden.di.DataSourceModule
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.*
import javax.inject.Inject

class DefaultGardenRepository @Inject constructor(
    @DataSourceModule.LocalGardenDataSource private val localDataSource: GardenDataSource) : GardenRepository {

    override fun plantTree(garden: Garden): Completable {
        return localDataSource.plantTree(garden)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getGarden(date: Date): Single<Garden> {
        return localDataSource.getGarden(date)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}