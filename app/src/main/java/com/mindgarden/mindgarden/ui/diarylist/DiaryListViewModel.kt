package com.mindgarden.mindgarden.ui.diarylist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DiaryListViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is DiaryList Fragment"
    }
    val text: LiveData<String> = _text
}