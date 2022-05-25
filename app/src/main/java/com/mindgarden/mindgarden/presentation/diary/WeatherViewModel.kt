package com.mindgarden.mindgarden.presentation.diary

import com.mindgarden.mindgarden.presentation.util.common.navigation.NavigationViewModel

class WeatherViewModel : NavigationViewModel() {

    fun goBack() {
        navigateBack()
    }
}