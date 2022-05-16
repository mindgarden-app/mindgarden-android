package com.mindgarden.mindgarden.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mindgarden.mindgarden.data.db.entity.Diary
import com.mindgarden.mindgarden.data.db.entity.Mind
import com.mindgarden.mindgarden.data.repository.diaryRepo.local.DiaryDao
import com.mindgarden.mindgarden.data.repository.gardenRepo.local.dao.GardenDao

@Database(entities = [Mind::class, Diary::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class MindGardenDatabase : RoomDatabase() {
    abstract fun gardenDao(): GardenDao
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