package com.mindgarden.mindgarden.di

import android.content.Context
import com.mindgarden.mindgarden.data.db.Converters
import com.mindgarden.mindgarden.data.db.dao.DiaryDao
import com.mindgarden.mindgarden.data.db.dao.GardenDao
import com.mindgarden.mindgarden.data.db.MindGardenDatabase
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
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
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    @Singleton
    @Provides
    fun provideMindGardenDatabase(@ApplicationContext ctx: Context, moshi: Moshi): MindGardenDatabase {
        Converters.initialize(moshi)
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