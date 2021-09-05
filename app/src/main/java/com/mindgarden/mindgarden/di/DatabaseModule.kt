package com.mindgarden.mindgarden.di

import android.content.Context
import com.mindgarden.mindgarden.data.repository.database.MindGardenDatabase
import com.mindgarden.mindgarden.data.repository.diaryRepo.local.DiaryDao
import com.mindgarden.mindgarden.data.repository.gardenRepo.local.dao.GardenDao
import com.mindgarden.mindgarden.data.repository.userRepo.local.dao.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Singleton
    @Provides
    fun provideMindGardenDatabase(@ApplicationContext ctx: Context): MindGardenDatabase {
        return MindGardenDatabase.getInstance(ctx)
    }

    @Provides
    fun provideDiaryDao(db: MindGardenDatabase): DiaryDao {
        return db.diaryDao()
    }

    @Provides
    fun provideGardenDao(db: MindGardenDatabase): GardenDao {
        return db.gardenDao()
    }

    @Provides
    fun userGardenDao(db: MindGardenDatabase): UserDao {
        return db.userDao()
    }
}