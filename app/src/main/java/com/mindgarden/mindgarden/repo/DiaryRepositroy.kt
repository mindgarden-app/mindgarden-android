package com.mindgarden.mindgarden.repo

import android.app.Application
import androidx.lifecycle.LiveData
import com.mindgarden.mindgarden.dao.DiaryDao
import com.mindgarden.mindgarden.dao.MindGardenDatabase
import com.mindgarden.mindgarden.model.Diary

//1. class DiaryRepository private constructor(private val diaryDao: DiaryDao)
class DiaryRepository(application: Application) {
    private val mindgardenDatabase = MindGardenDatabase.getInstance(application)!!
    private val diaryDao: DiaryDao = mindgardenDatabase.diaryDao()
    private val diaries: LiveData<List<Diary>> = diaryDao.getAll()

    fun getAll(): LiveData<List<Diary>> {
        return diaries
    }

    fun insert(diary: Diary) {
        try {
            val thread = Thread(Runnable {
                diaryDao.insert(diary)
            })
            thread.start()
        } catch (e: Exception) { }
    }

    fun delete(diary: Diary) {
        try {
            val thread = Thread(Runnable {
                diaryDao.delete(diary)
            })
            thread.start()
        } catch (e: Exception) { }
    }
}