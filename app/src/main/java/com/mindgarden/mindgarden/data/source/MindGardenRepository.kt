package com.mindgarden.mindgarden.data.source

import com.mindgarden.mindgarden.data.model.Diary
import com.mindgarden.mindgarden.data.model.Garden
import com.mindgarden.mindgarden.data.model.User
import com.mindgarden.mindgarden.data.source.local.MindGardenLocalDataSource

class MindGardenRepository (private val mindGardenLocalDataSource: MindGardenLocalDataSource) : MindGardenDataSource{

    override fun registerUser(user: User) {
        mindGardenLocalDataSource.registerUser(user)
    }

    override fun loadDiary(date: String): List<Diary> {
        return mindGardenLocalDataSource.loadDiary(date)
    }

    override fun writeDiary(diary: Diary) {
        mindGardenLocalDataSource.writeDiary(diary)
    }

    override fun updateDiary(diary: Diary): Int {
        return mindGardenLocalDataSource.updateDiary(diary)
    }

    override fun deleteDiary(diary: Diary): Int {
        return mindGardenLocalDataSource.deleteDiary(diary)
    }

    override fun plantTree(garden: Garden) {
        mindGardenLocalDataSource.plantTree(garden)
    }


}