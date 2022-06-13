package com.mindgarden.mindgarden.presentation.diary.read

import android.app.Application
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mindgarden.mindgarden.R
import com.mindgarden.mindgarden.presentation.util.common.navigation.NavigationViewModel
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class DiaryImageViewModel @AssistedInject constructor(
    @Assisted val size: Int,
    val application: Application
) : NavigationViewModel() {

    @AssistedFactory
    interface DiaryImageViewModelFactory {
        fun create(size: Int): DiaryImageViewModel
    }

    companion object {
        @Suppress("UNCHECKED_CAST")
        fun provideFactory(
            diaryImageViewModelFactory: DiaryImageViewModelFactory,
            size: Int
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return diaryImageViewModelFactory.create(size) as T
            }
        }
    }

    private val _currentPageInfo = MutableStateFlow(application.getString(R.string.diary_image_page, 1, size))
    val currentPageInfo: StateFlow<String> get() = _currentPageInfo

    fun onPageChange(pageNum: Int) {
        _currentPageInfo.value = application.getString(R.string.diary_image_page, pageNum, size)
        Log.d("DiaryImageViewModelk", "onPageChange: ${application.getString(R.string.diary_image_page, pageNum, size)}")
    }

    fun clickBackButton() {
        navigateBack()
    }

}