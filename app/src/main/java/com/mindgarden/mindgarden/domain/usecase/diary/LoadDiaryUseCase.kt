package com.mindgarden.mindgarden.domain.usecase.diary

import com.mindgarden.mindgarden.data.db.entity.Diary
import kotlinx.coroutines.flow.Flow
import com.mindgarden.mindgarden.domain.repository.DiaryRepository
import com.mindgarden.mindgarden.util.Result
import javax.inject.Inject

class LoadDiaryUseCase  @Inject constructor(private val diaryRepository: DiaryRepository) {
    operator fun invoke(idx: Long): Flow<Result<Diary>> = diaryRepository.getDiaryFlow(idx)
}