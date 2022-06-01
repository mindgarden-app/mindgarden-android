package com.mindgarden.mindgarden.presentation.util.common.navigation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavDirections
import com.mindgarden.mindgarden.presentation.util.common.Event
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Navigation Event 를 받을 수 있는 Base ViewModel 입니다
 */
abstract class NavigationViewModel : ViewModel() {

    val navigation = Event<NavigationCommand>()

    fun navigate(navDirections: NavDirections) {
        viewModelScope.launch(Dispatchers.Main) {
            navigation.publishEvent(NavigationCommand.ToDirection(navDirections))
        }
    }

    fun navigateBack() {
        viewModelScope.launch(Dispatchers.Main) {
            navigation.publishEvent(NavigationCommand.Back)
        }
    }

    fun popBackStackWithResult(key: String, result: Any) {
        viewModelScope.launch(Dispatchers.Main) {
            navigation.publishEvent(NavigationCommand.PopBackStackWithResult(key, result))
        }
    }
}