package com.mindgarden.mindgarden.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mindgarden.mindgarden.data.repository.gardenRepo.DefaultGardenRepository
import com.mindgarden.mindgarden.ui.util.base.BaseViewModel
import com.mindgarden.mindgarden.util.ext.getGardenDate
import com.mindgarden.mindgarden.util.ext.now
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val defaultGardenRepository: DefaultGardenRepository) : BaseViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text
}