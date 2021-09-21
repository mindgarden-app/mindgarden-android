package com.mindgarden.mindgarden.di

import com.mindgarden.mindgarden.data.repository.database.MindGardenDatabase
import com.mindgarden.mindgarden.data.repository.diaryRepo.DiaryDataSource
import com.mindgarden.mindgarden.data.repository.diaryRepo.local.LocalDiaryDataSource
import com.mindgarden.mindgarden.data.repository.gardenRepo.GardenDataSource
import com.mindgarden.mindgarden.data.repository.gardenRepo.local.LocalGardenDataSource
import com.mindgarden.mindgarden.data.repository.userRepo.UserDataSource
import com.mindgarden.mindgarden.data.repository.userRepo.local.LocalUserDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DataSourceModule {

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class LocalDiaryDataSource

    @Singleton
    @LocalDiaryDataSource
    @Provides
    fun provideDiaryDataSource(db: MindGardenDatabase): DiaryDataSource {
        return LocalDiaryDataSource(db.diaryDao())
    }

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class LocalGardenDataSource

    @Singleton
    @LocalGardenDataSource
    @Provides
    fun provideGardenDataSource(db: MindGardenDatabase): GardenDataSource {
        return LocalGardenDataSource(db.gardenDao())
    }

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class LocalUserDataSource

    @Singleton
    @LocalUserDataSource
    @Provides
    fun provideUserDataSource(db: MindGardenDatabase): UserDataSource {
        return LocalUserDataSource(db.userDao())
    }
}