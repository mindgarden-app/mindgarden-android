package com.mindgarden.mindgarden.data.repository.gardenRepo

import com.mindgarden.mindgarden.data.db.entity.Mind
import com.mindgarden.mindgarden.di.DataSourceModule
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import java.time.LocalDateTime
import javax.inject.Inject

class DefaultGardenRepository @Inject constructor(
    @DataSourceModule.LocalGardenDataSource private val localDataSource: GardenDataSource) : GardenRepository {

    override fun plantTree(mind: Mind): Completable {
        return localDataSource.plantTree(mind)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getGarden(date: LocalDateTime): Single<List<Mind>> {
        return localDataSource.getGarden(date)
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
    }
}