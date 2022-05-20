package com.mindgarden.mindgarden.domain.repository

import com.mindgarden.mindgarden.data.db.entity.Mind
import kotlinx.coroutines.flow.Flow
import com.mindgarden.mindgarden.util.Result

interface GardenRepository {
    suspend fun plantMind(mind: Mind): Long
    fun getGarden(date: String): Flow<Result<List<Mind>>>
}