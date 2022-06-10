package com.mindgarden.mindgarden.presentation.diary.weather

import com.mindgarden.mindgarden.presentation.diary.weather.WeatherFragment.Companion.WEATHER
import com.mindgarden.mindgarden.presentation.util.common.GardenToolbar
import com.mindgarden.mindgarden.presentation.util.common.GardenToolbarListener
import com.mindgarden.mindgarden.presentation.util.common.navigation.NavigationViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class WeatherViewModel : NavigationViewModel() {

    private val _weather = MutableStateFlow(WeatherType.Default)
    val weather: StateFlow<WeatherType> = _weather

    fun setWeather(weather: WeatherType) {
        _weather.value = weather
    }

    val weatherText = MutableStateFlow("")

    val toolbarListener = object : GardenToolbarListener {
        override val toolbarData: GardenToolbar
            get() = GardenToolbar.WeatherToolbar()

        override fun leftButtonClick() {
            navigateBack()
        }

        override fun rightButtonClick() {
            if (weatherText.value.isBlank()) {
                popBackStackWithResult(WEATHER, Weather(weather.value))
            } else {
                popBackStackWithResult(WEATHER, Weather(weather.value, weatherText.value))
            }
        }
    }
}