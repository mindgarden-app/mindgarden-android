package com.mindgarden.mindgarden.di

import com.mindgarden.mindgarden.data.repository.diaryRepo.DiaryDataSource
import com.mindgarden.mindgarden.data.repository.diaryRepo.DefaultDiaryRepository
import com.mindgarden.mindgarden.data.repository.diaryRepo.DiaryRepository
import com.mindgarden.mindgarden.data.repository.gardenRepo.DefaultGardenRepository
import com.mindgarden.mindgarden.data.repository.gardenRepo.GardenDataSource
import com.mindgarden.mindgarden.data.repository.gardenRepo.GardenRepository
import com.mindgarden.mindgarden.data.repository.userRepo.DefaultUserRepository
import com.mindgarden.mindgarden.data.repository.userRepo.UserDataSource
import com.mindgarden.mindgarden.data.repository.userRepo.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {
    @Singleton
    @Provides
    fun provideDiaryRepository(
       @DataSourceModule.LocalDiaryDataSource  localDiaryDatasource: DiaryDataSource
    ): DiaryRepository {
        return DefaultDiaryRepository(localDiaryDatasource)
    }

    @Singleton
    @Provides
    fun provideGardenRepository(
        @DataSourceModule.LocalGardenDataSource localGardenDataSource: GardenDataSource
    ): GardenRepository {
        return DefaultGardenRepository(localGardenDataSource)
    }

    @Singleton
    @Provides
    fun provideUserRepository(
        @DataSourceModule.LocalUserDataSource localUserDataSource: UserDataSource
    ): UserRepository {
        return DefaultUserRepository(localUserDataSource)
    }
}