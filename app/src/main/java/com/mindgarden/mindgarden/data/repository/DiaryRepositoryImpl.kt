package com.mindgarden.mindgarden.data.repository

import com.mindgarden.mindgarden.data.db.dao.DiaryDao
import com.mindgarden.mindgarden.data.db.entity.Diary
import com.mindgarden.mindgarden.data.db.entity.DiaryUpdate
import com.mindgarden.mindgarden.domain.repository.DiaryRepository
import com.mindgarden.mindgarden.util.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DiaryRepositoryImpl @Inject constructor(private val diaryDao: DiaryDao) : DiaryRepository {

    override suspend fun writeDiary(diary: Diary): Long {
        return diaryDao.insert(diary)
    }

    override suspend fun modifyDiary(newDiary: DiaryUpdate) {
        return diaryDao.updateDiary(newDiary)
    }

    override suspend fun deleteDiary(diary: Diary) {
        return diaryDao.delete(diary)
    }

    override fun getDiaryListFlow(date: String, isAsc: Boolean): Flow<Result<List<Diary>>> =
        flow<Result<List<Diary>>> {
            diaryDao.loadDiaryList(date, isAsc).collect {
                emit(Result.Success(it))
            }
        }.catch { e ->
            emit(Result.Error(e.localizedMessage, e))
        }

    override fun getDiaryFlow(idx: Long): Flow<Result<Diary>> = flow<Result<Diary>> {
        diaryDao.loadDiary(idx).collect { diary ->
            emit(Result.Success(diary))
        }
    }.catch { e ->
        emit(Result.Error(e.localizedMessage, e))
    }
}