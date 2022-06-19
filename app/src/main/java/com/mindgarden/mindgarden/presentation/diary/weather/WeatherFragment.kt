package com.mindgarden.mindgarden.presentation.diary.weather

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.SimpleItemAnimator
import com.mindgarden.mindgarden.R
import com.mindgarden.mindgarden.databinding.FragmentWeatherBinding
import com.mindgarden.mindgarden.presentation.util.common.navigation.NavigationFragment

class WeatherFragment :
    NavigationFragment<WeatherViewModel, FragmentWeatherBinding>(R.layout.fragment_weather) {

    override val viewModel: WeatherViewModel by viewModels()

    override fun setViewModel() {
        binding.vm = viewModel
    }

    private val weatherAdapter: WeatherAdapter by lazy {
        WeatherAdapter {
            viewModel.setWeather(WeatherType.values()[it])
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvWeather.apply {
            adapter = weatherAdapter
            (itemAnimator as SimpleItemAnimator).supportsChangeAnimations = false
        }
    }

    companion object {
        const val WEATHER = "weather_result"
    }
}