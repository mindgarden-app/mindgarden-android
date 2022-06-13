package com.mindgarden.mindgarden.presentation.diary.write

import android.app.Application
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.mindgarden.mindgarden.R
import com.mindgarden.mindgarden.data.db.entity.Diary
import com.mindgarden.mindgarden.data.db.entity.DiaryUpdate
import com.mindgarden.mindgarden.domain.usecase.diary.ModifyDiaryUseCase
import com.mindgarden.mindgarden.domain.usecase.diary.WriteDiaryUseCase
import com.mindgarden.mindgarden.presentation.diary.weather.Weather
import com.mindgarden.mindgarden.presentation.diary.weather.WeatherType
import com.mindgarden.mindgarden.presentation.util.common.GardenToolbar
import com.mindgarden.mindgarden.presentation.util.common.GardenToolbarListener
import com.mindgarden.mindgarden.presentation.util.common.UIState
import com.mindgarden.mindgarden.presentation.util.common.navigation.NavigationViewModel
import com.mindgarden.mindgarden.util.ext.now
import com.mindgarden.mindgarden.util.ext.toStringOfPattern
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import javax.annotation.Nullable

class WriteDiaryViewModel @AssistedInject constructor(
    private val application: Application,
    private val writeDiaryUseCase: WriteDiaryUseCase,
    private val modifyDiaryUseCase: ModifyDiaryUseCase,
    @Assisted @Nullable private val diaryFromRead: Diary?
) : NavigationViewModel() {

    @dagger.assisted.AssistedFactory
    interface WriteDiaryViewModelFactory {
        fun create(initParams: Diary?): WriteDiaryViewModel
    }

    companion object {
        fun provideFactory(
            writeDiaryViewModelFactory: WriteDiaryViewModelFactory,
            initParams: Diary?
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return writeDiaryViewModelFactory.create(initParams) as T
            }
        }
    }

    // weather
    private val _weather = MutableStateFlow(diaryFromRead?.weather ?: Weather(WeatherType.Default))
    val weather: StateFlow<Weather> = _weather

    fun setWeather(weather: Weather) {
        _weather.value = weather
    }

    fun goToWeatherFragment() {
        navigate(WriteDiaryFragmentDirections.actionWriteDiaryFragmentToWeatherFragment())
    }

    // images
    private val _images = MutableStateFlow<List<String>>(emptyList())
    val images: StateFlow<List<String>> = _images

    fun setImages(images: List<String>) {
        _images.value = images
    }

    val contents = MutableStateFlow(diaryFromRead?.contents ?: "")

    private val _date = MutableStateFlow(diaryFromRead?.date ?: now())
    val date: StateFlow<LocalDateTime> = _date

    val diary = combine(_weather, _images, contents, _date) { weather, list, content, date ->
        Diary(
            date,
            content,
            weather,
            list
        )
    }.stateIn(
        viewModelScope,
        SharingStarted.Eagerly,
        Diary(now(), "", Weather(WeatherType.Default), null)
    )

    private val _state = MutableStateFlow<UIState<Any>>(UIState.Loading)
    val state: StateFlow<UIState<Any>> get() = _state

    fun writeDiary() = viewModelScope.launch {
        Log.d("WriteDiaryViewModel", "writeDiary Click!")
        _state.value = UIState.Loading
        runCatching {
            if (diaryFromRead != null) {
                modifyDiaryUseCase.invoke(diaryFromRead.idx, DiaryUpdate.fromDiary(diary.value))
            } else {
                writeDiaryUseCase.invoke(diary.value)
            }
        }.onSuccess {
            _state.value = UIState.Success(it)
            navigate(WriteDiaryFragmentDirections.actionWriteDiaryFragmentToReadDiaryFragment(diary.value))
        }.onFailure {
            _state.value = UIState.Error(it as Error)
        }
    }

    val toolbarListener = object : GardenToolbarListener {
        override val toolbarData: GardenToolbar
            get() = GardenToolbar.WriteDiaryToolbar().copy(
                title = _date.value.toStringOfPattern(application.getString(R.string.pattern_toolbar_diary))
            )

        override fun leftButtonClick() {
            navigateBack()
        }

        override fun rightButtonClick() {
            writeDiary()
        }
    }
}
