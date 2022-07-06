package com.mindgarden.mindgarden.domain.repository

import com.mindgarden.mindgarden.data.db.entity.Diary
import com.mindgarden.mindgarden.data.db.entity.DiaryUpdate
import kotlinx.coroutines.flow.Flow
import com.mindgarden.mindgarden.util.Result
import java.time.LocalDateTime

interface DiaryRepository {
    suspend fun writeDiary(diary: Diary): Long
    suspend fun modifyDiary(newDiary: DiaryUpdate)
    suspend fun deleteDiary(diary: Diary)
    fun getDiaryListFlow(date: String, isAsc: Boolean): Flow<Result<List<Diary>>>
    fun getDiaryFlow(idx: Long): Flow<Result<Diary>>
    fun getDiaryCount(date: String): Int
}