package com.mindgarden.mindgarden.presentation.diary

import androidx.fragment.app.viewModels
import com.mindgarden.mindgarden.R
import com.mindgarden.mindgarden.databinding.FragmentWeatherBinding
import com.mindgarden.mindgarden.presentation.util.common.navigation.NavigationFragment

class WeatherFragment :
    NavigationFragment<WeatherViewModel, FragmentWeatherBinding>(R.layout.fragment_weather) {

    override val viewModel: WeatherViewModel by viewModels()

    override fun setViewModel() {
        binding.vm = viewModel
    }

}