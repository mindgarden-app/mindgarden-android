package com.mindgarden.mindgarden.data.viewmodelFactory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mindgarden.mindgarden.data.inject.RepositoryInjector
import com.mindgarden.mindgarden.data.repository.userRepo.UserRepository
import com.mindgarden.mindgarden.ui.nickname.NickNameViewModel

class UserViewModelFactory(private val userRepository: UserRepository) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        with(modelClass) {
            when {
                isAssignableFrom(NickNameViewModel::class.java) -> NickNameViewModel(userRepository)
                else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
            }
        } as T

    companion object {
        @Volatile
        private var INSTANCE: UserViewModelFactory? = null

        @JvmStatic
        fun getInstance(application: Application) =
            INSTANCE ?: synchronized(UserViewModelFactory::class.java) {
                INSTANCE ?: UserViewModelFactory(
                    RepositoryInjector.provideUserRepository(application.applicationContext)
                ).also { INSTANCE = it }
            }
    }
}