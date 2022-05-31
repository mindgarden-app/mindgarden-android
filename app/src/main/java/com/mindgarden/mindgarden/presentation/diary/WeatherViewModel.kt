package com.mindgarden.mindgarden.presentation.diary

import android.util.Log
import com.mindgarden.mindgarden.presentation.util.common.GardenToolbar
import com.mindgarden.mindgarden.presentation.util.common.GardenToolbarListener
import com.mindgarden.mindgarden.presentation.util.common.navigation.NavigationViewModel

class WeatherViewModel : NavigationViewModel() {


    val toolbarListener = object : GardenToolbarListener {
        override val toolbarData: GardenToolbar
            get() = GardenToolbar.WeatherToolbar()

        override fun leftButtonClick() {
            navigateBack()
        }

        override fun rightButtonClick() {
            Log.d("WeatherViewModel", "완료")
        }
    }
}