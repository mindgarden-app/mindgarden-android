package com.mindgarden.mindgarden.domain.usecase.diary

import com.mindgarden.mindgarden.data.db.entity.DiaryUpdate
import com.mindgarden.mindgarden.domain.repository.DiaryRepository
import javax.inject.Inject

class ModifyDiaryUseCase @Inject constructor(private val diaryRepository: DiaryRepository) {
    suspend operator fun invoke(oldDiaryIdx: Long, diary: DiaryUpdate) =
        diaryRepository.modifyDiary(
            DiaryUpdate(
                idx = oldDiaryIdx,
                contents = diary.contents,
                weatherText = diary.weatherText,
                weather = diary.weather,
                img = diary.img
            )
        )
}