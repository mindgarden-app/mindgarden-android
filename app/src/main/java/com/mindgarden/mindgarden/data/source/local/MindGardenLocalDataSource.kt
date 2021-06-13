package com.mindgarden.mindgarden.data.source.local

import com.mindgarden.mindgarden.data.model.Diary
import com.mindgarden.mindgarden.data.model.Garden
import com.mindgarden.mindgarden.data.model.User
import com.mindgarden.mindgarden.data.source.MindGardenDataSource

class MindGardenLocalDataSource(
    val gardenDao: GardenDao,
    val diaryDao: DiaryDao,
    val userDao: UserDao
) : MindGardenDataSource {

    override fun registerUser(user: User) {
        return userDao.registerUser(user)
    }

    override fun loadDiary(date: String): List<Diary> {
        return diaryDao.loadDiary(date)
    }

    override fun writeDiary(diary: Diary) {
        return diaryDao.writeDiary(diary)
    }

    override fun updateDiary(diary: Diary): Int {
       return diaryDao.updateDiary(diary)
    }

    override fun deleteDiary(diary: Diary): Int {
        return diaryDao.deleteDiary(diary)
    }

    override fun plantTree(garden: Garden) {
        return gardenDao.plantTree(garden)
    }

}