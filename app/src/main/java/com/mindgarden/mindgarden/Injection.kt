package com.mindgarden.mindgarden

import android.content.Context
import com.mindgarden.mindgarden.data.source.MindGardenRepository
import com.mindgarden.mindgarden.data.source.local.MindGardenDatabase
import com.mindgarden.mindgarden.data.source.local.MindGardenLocalDataSource

object Injection {
    private var database:MindGardenDatabase? = null

    @Volatile
    var mindGardenRepository: MindGardenRepository?=null

    fun provideMindGardenRepository(context: Context): MindGardenRepository {
        synchronized(this){
            return mindGardenRepository
                ?: createMindGardenRepository(
                    context
                )
        }
    }

    private fun createMindGardenRepository(context: Context): MindGardenRepository {
        val repo = MindGardenRepository(
            createMindGardenLocalDataSource(
                context
            )
        )
        mindGardenRepository =repo
        return repo
    }

    private fun createMindGardenLocalDataSource(context: Context):MindGardenLocalDataSource{
        val database = database ?: MindGardenDatabase.getInstance(context)
        return MindGardenLocalDataSource(database.gardenDao(),database.diaryDao(),database.userDao())
    }

}