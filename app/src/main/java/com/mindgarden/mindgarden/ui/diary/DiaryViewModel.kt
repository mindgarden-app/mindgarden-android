package com.mindgarden.mindgarden.ui.diary

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mindgarden.mindgarden.data.repository.diaryRepo.DiaryRepository
import com.mindgarden.mindgarden.util.base.BaseViewModel

class DiaryViewModel(private val diaryRepository: DiaryRepository) : BaseViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Diary Fragment"
    }
    val text: LiveData<String> = _text

    private val _isDataLoaded = MutableLiveData<Boolean>(false)
    val isDataLoaded: LiveData<Boolean> = _isDataLoaded

    init {
        Log.d("DiaryViewModel", "Diary ViewModel init")
    }

    fun loadData() {
        // get Data
        _isDataLoaded.value?.let {
            _isDataLoaded.value = !isDataLoaded.value!!
            Log.d("DiaryViewModel", "loadData: ${_isDataLoaded.value}")
            if (it) _text.value = "load data success"
            else _text.value = "data loading"
        }
    }
}