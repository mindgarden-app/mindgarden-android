package com.mindgarden.mindgarden.di

import android.content.Context
import com.mindgarden.mindgarden.data.db.dao.DiaryDao
import com.mindgarden.mindgarden.data.db.dao.GardenDao
import com.mindgarden.mindgarden.data.db.MindGardenDatabase
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
}