package com.mindgarden.mindgarden.factory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mindgarden.mindgarden.repo.DiaryRepository
import com.mindgarden.mindgarden.ui.diarylist.DiaryListViewModel

class DiaryListViewModelFactory(
    private val application: Application
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DiaryListViewModel(application) as T
    }
}