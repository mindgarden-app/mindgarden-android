package com.mindgarden.mindgarden.data.repository.diaryRepo.local

import androidx.room.*
import com.mindgarden.mindgarden.data.db.entity.Diary
import com.mindgarden.mindgarden.data.repository.common.BaseDao
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single
import java.util.*

@Dao
abstract class DiaryDao : BaseDao<Diary> {

    // TODO : paging library 추가
    @Query("SELECT * From diary WHERE date=:date")
    abstract fun loadDiaries(date: Date): Flowable<List<Diary>>

    @Query("SELECT * From diary WHERE idx=:idx")
    abstract fun loadDiaryById(idx: Long): Single<Diary>

    //TODO : month 별로 diary 삭제하는 sql 작성
//    @Query("")
//    abstract fun deleteDiaryByMonth(date: Date): Completable

    @Query("DELETE From diary")
    abstract fun deleteAllDiary(): Completable

    // TODO : 조회 1차 작업
    @Query("SELECT * From diary")
    abstract fun loadAll(): Flowable<List<Diary>>
}