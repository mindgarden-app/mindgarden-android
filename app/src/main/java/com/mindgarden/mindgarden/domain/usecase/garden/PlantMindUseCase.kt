package com.mindgarden.mindgarden.domain.usecase.garden

import com.mindgarden.mindgarden.data.db.entity.Mind
import com.mindgarden.mindgarden.domain.repository.GardenRepository
import javax.inject.Inject

class PlantMindUseCase @Inject constructor(private val gardenRepository: GardenRepository) {
    suspend operator fun invoke(mind: Mind) = gardenRepository.plantMind(mind)
}