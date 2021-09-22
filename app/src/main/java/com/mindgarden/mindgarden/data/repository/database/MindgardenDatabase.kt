package com.mindgarden.mindgarden.data.repository.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mindgarden.mindgarden.data.model.entity.Diary
import com.mindgarden.mindgarden.data.model.entity.Garden
import com.mindgarden.mindgarden.data.model.entity.User
import com.mindgarden.mindgarden.data.repository.common.Converters
import com.mindgarden.mindgarden.data.repository.diaryRepo.local.DiaryDao
import com.mindgarden.mindgarden.data.repository.gardenRepo.local.dao.GardenDao
import com.mindgarden.mindgarden.data.repository.userRepo.local.dao.UserDao

@Database(entities = [Garden::class, Diary::class, User::class], version = 2, exportSchema = false)
@TypeConverters(Converters::class)
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
            ).fallbackToDestructiveMigration().build()

    }
}