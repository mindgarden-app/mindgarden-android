package com.mindgarden.mindgarden.data.source

import com.mindgarden.mindgarden.data.model.Diary
import com.mindgarden.mindgarden.data.model.Garden
import com.mindgarden.mindgarden.data.model.User

interface MindGardenDataSource{
    fun registerUser(user: User)
    fun loadDiary(date: String): List<Diary>
    fun writeDiary(diary: Diary)
    fun updateDiary(diary: Diary):Int
    fun deleteDiary(diary: Diary):Int
    fun plantTree(garden: Garden)
}