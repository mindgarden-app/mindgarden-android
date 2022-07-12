package com.mindgarden.mindgarden.domain.usecase.garden

import com.mindgarden.mindgarden.domain.repository.GardenRepository
import javax.inject.Inject

class GetMindCountUseCase @Inject constructor(private val gardenRepository: GardenRepository) {
    operator fun invoke(date: String) = gardenRepository.getMindCount(date)
}