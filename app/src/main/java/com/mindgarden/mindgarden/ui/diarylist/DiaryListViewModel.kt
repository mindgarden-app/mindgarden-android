package com.mindgarden.mindgarden.ui.diarylist

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mindgarden.mindgarden.dao.DiaryDao
import com.mindgarden.mindgarden.dao.MindGardenDatabase
import com.mindgarden.mindgarden.model.Diary
import com.mindgarden.mindgarden.repo.DiaryRepository
import com.mindgarden.mindgarden.util.base.BaseViewModel

//1. class DiaryListViewModel(private val repo : DiaryRepository)
class DiaryListViewModel(application: Application) : ViewModel() {
    /*
    private val _text = MutableLiveData<String>().apply {
        value = "This is DiaryList Fragment"
    }
    val text: LiveData<String> = _text
     */

    private val repo = DiaryRepository(application)
    private val diaries = repo.getAll()

    fun getAll(): LiveData<List<Diary>> {
        return this.diaries
    }

    fun insert(diary: Diary) {
        repo.insert(diary)
    }

    fun delete(diary: Diary) {
        repo.delete(diary)
    }
}