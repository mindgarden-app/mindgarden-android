package com.mindgarden.mindgarden.data.repository.diaryRepo

import com.mindgarden.mindgarden.data.model.entity.Diary
import com.mindgarden.mindgarden.di.DataSourceModule
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.*
import javax.inject.Inject

class DefaultDiaryRepository @Inject constructor(
    @DataSourceModule.LocalDiaryDataSource private val localDataSource: DiaryDataSource) : DiaryRepository {

    override fun writeDiary(diary: Diary): Completable {
        return localDataSource.writeDiary(diary)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun editDiary(diary: Diary): Completable {
        return localDataSource.editDiary(diary)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun deleteAll(): Completable {
        return localDataSource.deleteAll()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun deleteDiary(diary: Diary): Completable {
        return localDataSource.deleteDiary(diary)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getDiaries(date: Date): Flowable<List<Diary>> {
        return localDataSource.getDiaries(date)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getDiary(id: Long): Single<Diary> {
        return localDataSource.getDiary(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    // TODO : 조회 1차 작업
    override fun getAll(): Flowable<List<Diary>> {
        return localDataSource.getAll()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}