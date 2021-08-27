package com.mindgarden.mindgarden.ui.nickname

import androidx.lifecycle.ViewModel
import com.mindgarden.mindgarden.data.repository.userRepo.DefaultUserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NickNameViewModel @Inject constructor(
    val repositoryDefault: DefaultUserRepository) : ViewModel() {

}