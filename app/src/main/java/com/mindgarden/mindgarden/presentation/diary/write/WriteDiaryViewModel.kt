package com.mindgarden.mindgarden.presentation.diary.write

import android.app.Application
import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.mindgarden.mindgarden.R
import com.mindgarden.mindgarden.data.db.entity.Diary
import com.mindgarden.mindgarden.domain.usecase.diary.WriteDiaryUseCase
import com.mindgarden.mindgarden.presentation.diary.write.WriteDiaryFragment.Companion.SAVED_DIARY
import com.mindgarden.mindgarden.presentation.util.common.GardenToolbar
import com.mindgarden.mindgarden.presentation.util.common.GardenToolbarListener
import com.mindgarden.mindgarden.presentation.util.common.UIState
import com.mindgarden.mindgarden.presentation.util.common.navigation.NavigationViewModel
import com.mindgarden.mindgarden.util.ext.now
import com.mindgarden.mindgarden.util.ext.toStringOfPattern
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WriteDiaryViewModel @Inject constructor(
    private val application: Application,
    private val stateHandle: SavedStateHandle,
    private val writeDiaryUseCase: WriteDiaryUseCase
) : NavigationViewModel() {

    var currentDiary: Diary? = null
        set(value) {
            stateHandle.set(SAVED_DIARY, value)
            field = value
        }

    init {
        stateHandle.get<Diary>(SAVED_DIARY)?.run {
            Log.d("WriteDiaryViewModel", "savedStateHandle: $this")
            currentDiary = this
        }
    }

    private val _state = MutableStateFlow<UIState<Long>>(UIState.Loading)
    val state: StateFlow<UIState<Long>> get() = _state

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
        navigate(WriteDiaryFragmentDirections.actionWriteDiaryFragmentToWeatherFragment())
    }

    val toolbarListener = object : GardenToolbarListener {
        val pattern = application.getString(R.string.pattern_toolbar_diary)
        override val toolbarData: GardenToolbar
            get() = GardenToolbar.WriteDiaryToolbar().copy(
                title =
                currentDiary?.date?.toStringOfPattern(pattern) ?: now().toStringOfPattern(pattern)
            )

        override fun leftButtonClick() {
            navigateBack()
        }

        override fun rightButtonClick() {
            writeDiary()
        }
    }
}
