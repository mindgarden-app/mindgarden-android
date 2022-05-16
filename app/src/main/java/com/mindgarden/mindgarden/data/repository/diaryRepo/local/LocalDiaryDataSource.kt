package com.mindgarden.mindgarden.data.repository.diaryRepo.local

import com.mindgarden.mindgarden.data.db.entity.Diary
import com.mindgarden.mindgarden.data.repository.diaryRepo.DiaryDataSource
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single
import java.util.*

class LocalDiaryDataSource(private val diaryDao: DiaryDao) : DiaryDataSource {
    override fun writeDiary(diary: Diary): Completable {
        return diaryDao.insert(diary)
    }

    override fun editDiary(diary: Diary): Completable {
        return diaryDao.update(diary)
    }

    override fun deleteAll(): Completable {
        return diaryDao.deleteAllDiary()
    }

    override fun deleteDiary(diary: Diary): Completable {
        return diaryDao.delete(diary)
    }

    override fun getDiaries(date: Date): Flowable<List<Diary>> {
        return diaryDao.loadDiaries(date)
    }

    override fun getDiary(id: Long): Single<Diary> {
        return diaryDao.loadDiaryById(id)

    }

    // TODO : 조회 1차 작업
    override fun getAll(): Flowable<List<Diary>> {
        return diaryDao.loadAll()
    }
}