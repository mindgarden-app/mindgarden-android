package com.mindgarden.mindgarden.presentation.diary.weather

import com.mindgarden.mindgarden.presentation.diary.weather.WeatherFragment.Companion.WEATHER
import com.mindgarden.mindgarden.presentation.util.common.GardenToolbar
import com.mindgarden.mindgarden.presentation.util.common.GardenToolbarListener
import com.mindgarden.mindgarden.presentation.util.common.navigation.NavigationViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class WeatherViewModel : NavigationViewModel() {

    private val _weather = MutableStateFlow(Weather.Default)
    val weather: StateFlow<Weather> = _weather

    init {
        _weather.value.customText = ""
    }

    fun setWeather(weather: Weather) {
        _weather.value = weather
    }

    val weatherText = MutableStateFlow<String?>(null)

    val toolbarListener = object : GardenToolbarListener {
        override val toolbarData: GardenToolbar
            get() = GardenToolbar.WeatherToolbar()

        override fun leftButtonClick() {
            navigateBack()
        }

        override fun rightButtonClick() {
            if (weatherText.value.isNullOrBlank()) {
                popBackStackWithResult(WEATHER, weather.value)
            } else {
                _weather.value.customText = weatherText.value!!
                popBackStackWithResult(WEATHER, weather.value)
            }
        }
    }
}