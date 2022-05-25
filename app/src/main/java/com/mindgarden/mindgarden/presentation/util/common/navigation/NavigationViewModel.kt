package com.mindgarden.mindgarden.presentation.util.common.navigation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavDirections
import com.mindgarden.mindgarden.presentation.util.common.navigation.NavigationCommand
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

abstract class NavigationViewModel : ViewModel() {

    private val _navigation = MutableSharedFlow<NavigationCommand>()
    val navigation = _navigation.asSharedFlow()

    fun navigate(navDirections: NavDirections) {
        viewModelScope.launch(Dispatchers.Main) {
            _navigation.emit(NavigationCommand.ToDirection(navDirections))
        }
    }

    fun navigateBack() {
        viewModelScope.launch(Dispatchers.Main) {
            _navigation.emit(NavigationCommand.Back)
        }
    }
}