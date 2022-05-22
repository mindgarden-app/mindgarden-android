package com.mindgarden.mindgarden.domain.usecase.diary

import com.mindgarden.mindgarden.data.db.entity.Diary
import com.mindgarden.mindgarden.domain.repository.DiaryRepository
import com.mindgarden.mindgarden.util.Result
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoadDiaryListUseCase @Inject constructor(private val diaryRepository: DiaryRepository) {

    operator fun invoke(date: String, isAsc: Boolean): Flow<Result<List<Diary>>> =
        diaryRepository.getDiaryListFlow(date, isAsc)
}