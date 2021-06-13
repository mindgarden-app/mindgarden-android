package com.mindgarden.mindgarden

import android.content.Context
import com.mindgarden.mindgarden.data.source.MindGardenRepository
import com.mindgarden.mindgarden.data.source.local.MindGardenDatabase
import com.mindgarden.mindgarden.data.source.local.MindGardenLocalDataSource

object ServiceLocator {
    private var database: MindGardenDatabase? = null

    @Volatile
    var mindGardenRepository: MindGardenRepository? = null

    fun provideMindGardenRepository(context: Context): MindGardenRepository {
        synchronized(this) {
            return mindGardenRepository
                ?: createMindGardenRepository(
                    context
                )
        }
    }

    private fun createMindGardenRepository(context: Context): MindGardenRepository {
        val database = database ?: MindGardenDatabase.getInstance(context)
        val localDataSource =
            MindGardenLocalDataSource(database.gardenDao(), database.diaryDao(), database.userDao())
        val repo = MindGardenRepository(localDataSource)
        mindGardenRepository = repo
        return repo
    }


}