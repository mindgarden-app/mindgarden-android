package com.mindgarden.mindgarden.data.viewmodelFactory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mindgarden.mindgarden.data.inject.RepositoryInjector
import com.mindgarden.mindgarden.data.repository.diaryRepo.DiaryRepository
import com.mindgarden.mindgarden.ui.diary.DiaryViewModel
import com.mindgarden.mindgarden.ui.diarylist.DiaryListViewModel

@Suppress("UNCHECKED_CAST")
class DiaryViewModelFactory(private val diaryRepository: DiaryRepository) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        with(modelClass) {
            when {
                isAssignableFrom(DiaryViewModel::class.java) -> DiaryViewModel(diaryRepository)
                isAssignableFrom(DiaryListViewModel::class.java) -> DiaryListViewModel(diaryRepository)
                else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
            }
        } as T

    companion object {
        @Volatile
        private var INSTANCE: DiaryViewModelFactory? = null

        @JvmStatic
        fun getInstance(application: Application) =
            INSTANCE ?: synchronized(DiaryViewModelFactory::class.java) {
                INSTANCE ?: DiaryViewModelFactory(
                    RepositoryInjector.provideDiaryRepository(application.applicationContext)
                ).also { INSTANCE = it }
            }
    }
}