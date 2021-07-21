package com.mindgarden.mindgarden.data.viewmodelFactory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mindgarden.mindgarden.data.inject.RepositoryInjector
import com.mindgarden.mindgarden.data.repository.gardenRepo.GardenRepository
import com.mindgarden.mindgarden.ui.home.HomeViewModel
import com.mindgarden.mindgarden.ui.inventory.InventoryViewModel
import java.lang.IllegalArgumentException

@Suppress("UNCHECKED_CAST")
class GardenViewModelFactory(private val gardenRepository: GardenRepository) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        with(modelClass) {
            when {
                isAssignableFrom(HomeViewModel::class.java) -> HomeViewModel(gardenRepository)
                isAssignableFrom(InventoryViewModel::class.java) -> InventoryViewModel(gardenRepository)
                else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
            }
        } as T

    companion object {
        @Volatile
        private var INSTANCE: GardenViewModelFactory? = null

        @JvmStatic
        fun getInstance(application: Application) =
            INSTANCE ?: synchronized(GardenViewModelFactory::class.java) {
            INSTANCE ?: GardenViewModelFactory(
                RepositoryInjector.provideGardenRepository(application.applicationContext)
            ).also { INSTANCE = it }
        }
    }
}