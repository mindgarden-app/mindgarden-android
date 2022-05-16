package com.mindgarden.mindgarden

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.mindgarden.mindgarden.data.db.MindGardenDatabase
import com.mindgarden.mindgarden.data.db.dao.DiaryDao
import org.junit.After
import org.junit.Before
import java.io.IOException

class DiaryDatabaseTest {
    lateinit var db: MindGardenDatabase
    lateinit var diaryDao: DiaryDao

    @Before
    fun createDB() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, MindGardenDatabase::class.java).build()
        diaryDao = db.diaryDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }
}