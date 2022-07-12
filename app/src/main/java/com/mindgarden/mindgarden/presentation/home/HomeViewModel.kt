package com.mindgarden.mindgarden.presentation.home

import android.app.Application
import android.util.Log
import androidx.lifecycle.viewModelScope
import com.mindgarden.mindgarden.R
import com.mindgarden.mindgarden.domain.usecase.garden.LoadGardenUseCase
import com.mindgarden.mindgarden.presentation.inventory.model.InventoryMind.Companion.convertInventoryMind
import com.mindgarden.mindgarden.presentation.util.common.navigation.NavigationViewModel
import com.mindgarden.mindgarden.util.Result
import com.mindgarden.mindgarden.util.ext.now
import com.mindgarden.mindgarden.util.ext.toStringOfPattern
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    application: Application,
    private val loadGardenUseCase: LoadGardenUseCase
) : NavigationViewModel() {

    private val _date =
        MutableStateFlow(now().toStringOfPattern(application.getString(R.string.pattern_calendar)))
    val date: StateFlow<String> = _date

    private var isEmptyGarden = true

    init {
        loadGarden()
    }

    private fun loadGarden() = viewModelScope.launch {
        loadGardenUseCase.invoke(_date.value).collect { result ->
            when (result) {
                is Result.Error -> {
                    Log.d("InventoryViewModel", "error - ${result.errorMsg} ")
                }
                is Result.Success -> {
                    if (result.data.isNotEmpty()) {
                        isEmptyGarden = false
                    }
                }
            }
        }
    }

    fun goInventoryFragment() {
        navigate(HomeFragmentDirections.actionDestHomeToDestInventory(_date.value, isEmptyGarden))
    }
}