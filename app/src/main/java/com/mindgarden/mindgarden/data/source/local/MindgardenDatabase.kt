package com.mindgarden.mindgarden.data.source.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mindgarden.mindgarden.data.model.Diary
import com.mindgarden.mindgarden.data.model.Garden
import com.mindgarden.mindgarden.data.model.User

@Database(entities = [Garden::class,Diary::class, User::class], version = 1)
abstract class MindGardenDatabase : RoomDatabase() {
    abstract fun gardenDao(): GardenDao
    abstract fun userDao(): UserDao
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