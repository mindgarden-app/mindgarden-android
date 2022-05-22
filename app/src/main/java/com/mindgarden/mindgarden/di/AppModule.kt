package com.mindgarden.mindgarden.di

import com.mindgarden.mindgarden.data.db.dao.DiaryDao
import com.mindgarden.mindgarden.data.db.dao.GardenDao
import com.mindgarden.mindgarden.domain.repository.DiaryRepository
import com.mindgarden.mindgarden.data.repository.DiaryRepositoryImpl
import com.mindgarden.mindgarden.domain.repository.GardenRepository
import com.mindgarden.mindgarden.data.repository.GardenRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {
    @Singleton
    @Provides
    fun provideDiaryRepository(diaryDao: DiaryDao): DiaryRepository {
        return DiaryRepositoryImpl(diaryDao)
    }

    @Singleton
    @Provides
    fun provideGardenRepository(gardenDao: GardenDao): GardenRepository {
        return GardenRepositoryImpl(gardenDao)
    }
}