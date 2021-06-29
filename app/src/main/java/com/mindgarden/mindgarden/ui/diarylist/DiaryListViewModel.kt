package com.mindgarden.mindgarden.ui.diarylist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mindgarden.mindgarden.data.repository.diaryRepo.DiaryRepository

class DiaryListViewModel(private val diaryRepository: DiaryRepository) : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is DiaryList Fragment"
    }
    val text: LiveData<String> = _text

    init {
        // TODO : diaryRepository.getDiaries(date)
    }
}