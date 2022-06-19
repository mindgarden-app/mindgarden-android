package com.mindgarden.mindgarden.presentation.util.common.navigation

import androidx.navigation.NavDirections

sealed class NavigationCommand {
    data class ToDirection(val directions: NavDirections) : NavigationCommand()
    data class PopBackStackWithResult(val key: String, val result: Any) : NavigationCommand()
    object Back : NavigationCommand()
}