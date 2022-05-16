package com.mindgarden.mindgarden.data.repository.diaryRepo

import com.mindgarden.mindgarden.data.db.entity.Diary
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single
import java.util.*

// TODO("수정 필요")
interface DiaryRepository {
    fun writeDiary(diary: Diary): Completable
    fun editDiary(diary: Diary): Completable
    fun deleteAll(): Completable
    fun deleteDiary(diary: Diary): Completable
    fun getDiaries(date: Date): Flowable<List<Diary>>
    fun getDiary(id: Long): Single<Diary>

    // TODO : 조회 1차 작업
    fun getAll() : Flowable<List<Diary>>
}