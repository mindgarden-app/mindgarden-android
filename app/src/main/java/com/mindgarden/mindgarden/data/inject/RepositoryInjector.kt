package com.mindgarden.mindgarden.data.inject

import android.content.Context
import com.mindgarden.mindgarden.data.repository.diaryRepo.DiaryRepository
import com.mindgarden.mindgarden.data.repository.diaryRepo.local.LocalDiaryDataSource
import com.mindgarden.mindgarden.data.repository.gardenRepo.local.LocalGardenDataSource
import com.mindgarden.mindgarden.data.repository.database.MindGardenDatabase
import com.mindgarden.mindgarden.data.repository.gardenRepo.GardenRepository
import com.mindgarden.mindgarden.data.repository.userRepo.UserRepository
import com.mindgarden.mindgarden.data.repository.userRepo.local.LocalUserDataSource

object RepositoryInjector {

    private fun provideDatabase(context: Context): MindGardenDatabase {
        return MindGardenDatabase.getInstance(context)
    }

    @Volatile
    var diaryRepository: DiaryRepository? = null

    fun provideDiaryRepository(context: Context): DiaryRepository {
        synchronized(this) {
            return diaryRepository
                ?: DiaryRepository(LocalDiaryDataSource(provideDatabase(context).diaryDao()))
        }
    }

    @Volatile
    var gardenRepository: GardenRepository? = null

    fun provideGardenRepository(context: Context): GardenRepository {
        synchronized(this) {
            return gardenRepository
                ?: GardenRepository(LocalGardenDataSource(provideDatabase(context).gardenDao()))
        }
    }

    @Volatile
    var userRepository: UserRepository? = null

    fun provideUserRepository(context: Context): UserRepository {
        synchronized(this) {
            return userRepository
                ?: UserRepository( LocalUserDataSource(provideDatabase(context).userDao()))
        }
    }
}