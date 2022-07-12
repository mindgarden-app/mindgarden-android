package com.mindgarden.mindgarden.data.repository

import com.mindgarden.mindgarden.data.db.dao.GardenDao
import com.mindgarden.mindgarden.data.db.entity.Mind
import com.mindgarden.mindgarden.domain.repository.GardenRepository
import com.mindgarden.mindgarden.util.Result
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class GardenRepositoryImpl @Inject constructor(private val gardenDao: GardenDao) :
    GardenRepository {

    override suspend fun plantMind(mind: Mind): Long {
        return gardenDao.insert(mind)
    }

    override fun getGarden(date: String): Flow<Result<List<Mind>>> = flow<Result<List<Mind>>> {
        gardenDao.loadGarden(date).collect { emit(Result.Success(it)) }
    }.catch { e ->
        emit(Result.Error(e.localizedMessage, e))
    }

    override fun getMindCount(date: String): Int = gardenDao.getMindCount(date)

}