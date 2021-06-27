package com.mindgarden.mindgarden.data.source.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mindgarden.mindgarden.data.model.Diary

@Database(entities = [Diary::class], version = 1)
abstract class MindGardenDatabase : RoomDatabase() {
    abstract fun diaryDao(): DiaryDao

    companion object {
        @Volatile
        private var INSTANCE: MindGardenDatabase? = null

        fun getInstance(context: Context): MindGardenDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                MindGardenDatabase::class.java, "mindgarden.db"
            ).build()
    }
}