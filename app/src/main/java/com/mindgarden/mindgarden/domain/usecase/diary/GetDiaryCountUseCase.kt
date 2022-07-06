package com.mindgarden.mindgarden.domain.usecase.diary

import com.mindgarden.mindgarden.domain.repository.DiaryRepository
import javax.inject.Inject

class GetDiaryCountUseCase @Inject constructor(private val diaryRepository: DiaryRepository) {
    operator fun invoke(date: String): Int = diaryRepository.getDiaryCount(date)
}