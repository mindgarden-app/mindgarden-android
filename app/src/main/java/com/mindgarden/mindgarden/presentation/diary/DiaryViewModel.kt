package com.mindgarden.mindgarden.presentation.diary

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.mindgarden.mindgarden.data.db.entity.Diary
import com.mindgarden.mindgarden.domain.usecase.diary.WriteDiaryUseCase
import com.mindgarden.mindgarden.presentation.util.common.navigation.NavigationViewModel
import com.mindgarden.mindgarden.presentation.util.common.UIState
import com.mindgarden.mindgarden.util.ext.now
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DiaryViewModel @Inject constructor(
    private val writeDiaryUseCase: WriteDiaryUseCase) : NavigationViewModel() {

    private val _state = MutableStateFlow<UIState<Long>>(UIState.Loading)
    val state: StateFlow<UIState<Long>> get() = _state

    init {

    }

    private val mockDiary = Diary(
        now(),
        "time: ${now()} \n this is mock",
        weather = 0,
        weatherText = "this is weather text",
        img = null
    )

    fun writeDiary() = viewModelScope.launch {
        _state.value = UIState.Loading
        runCatching {
            writeDiaryUseCase.invoke(mockDiary)
        }.onSuccess {
            _state.value = UIState.Success(it)
        }.onFailure {
            _state.value = UIState.Error(it as Error)
        }
    }

    fun goToWeatherFragment() {
        navigate(DiaryFragmentDirections.actionDiaryFragmentToWeatherFragment())
    }
}
