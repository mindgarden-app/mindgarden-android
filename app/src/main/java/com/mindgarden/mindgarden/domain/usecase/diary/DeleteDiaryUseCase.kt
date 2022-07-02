package com.mindgarden.mindgarden.domain.usecase.diary

import com.mindgarden.mindgarden.data.db.entity.Diary
import com.mindgarden.mindgarden.domain.repository.DiaryRepository
import javax.inject.Inject

class DeleteDiaryUseCase @Inject constructor(private val diaryRepository: DiaryRepository) {
    suspend operator fun invoke(diary: Diary) = diaryRepository.deleteDiary(diary)
}