package com.mindgarden.mindgarden.presentation.home

import android.app.Application
import com.mindgarden.mindgarden.R
import com.mindgarden.mindgarden.domain.usecase.garden.LoadGardenUseCase
import com.mindgarden.mindgarden.presentation.util.common.navigation.NavigationViewModel
import com.mindgarden.mindgarden.util.ext.now
import com.mindgarden.mindgarden.util.ext.toStringOfPattern
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val application: Application,
    private val loadGardenUseCase: LoadGardenUseCase
) : NavigationViewModel() {

    private val _date =
        MutableStateFlow(now().toStringOfPattern(application.getString(R.string.pattern_calendar)))
    val date: StateFlow<String> = _date

    fun goInventoryFragment() {
        navigate(HomeFragmentDirections.actionDestHomeToDestInventory(_date.value))
    }
}