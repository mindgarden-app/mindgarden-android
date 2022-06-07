package com.mindgarden.mindgarden.presentation.diary.write

import android.app.Application
import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.mindgarden.mindgarden.R
import com.mindgarden.mindgarden.data.db.entity.Diary
import com.mindgarden.mindgarden.domain.usecase.diary.WriteDiaryUseCase
import com.mindgarden.mindgarden.presentation.diary.weather.Weather
import com.mindgarden.mindgarden.presentation.diary.write.WriteDiaryFragment.Companion.SAVED_DIARY
import com.mindgarden.mindgarden.presentation.util.common.GardenToolbar
import com.mindgarden.mindgarden.presentation.util.common.GardenToolbarListener
import com.mindgarden.mindgarden.presentation.util.common.UIState
import com.mindgarden.mindgarden.presentation.util.common.navigation.NavigationViewModel
import com.mindgarden.mindgarden.util.ext.now
import com.mindgarden.mindgarden.util.ext.toStringOfPattern
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class WriteDiaryViewModel @Inject constructor(
    private val application: Application,
    private val stateHandle: SavedStateHandle,
    private val writeDiaryUseCase: WriteDiaryUseCase
) : NavigationViewModel() {

    // from readDiary
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

        currentDiary?.let { diary ->
            _weather.value = Weather.values()[diary.weather]
            _weather.value.customText = diary.weatherText
            diary.img?.let { _images.value = it }
            contents.value = diary.contents
            _date.value = diary.date
        }
    }

    // weather
    private val _weather = MutableStateFlow(Weather.Default)
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

    val contents = MutableStateFlow<String?>(null)

    private val _date = MutableStateFlow(now())
    val date: StateFlow<LocalDateTime> = _date

    val diary = combine(_weather, _images, contents, _date) { weather, list, content, date ->
        Diary(
            date,
            content ?: "",
            weather.ordinal,
            weather.customText.ifEmpty { weather.defaultText },
            list
        )
    }.stateIn(
        viewModelScope,
        SharingStarted.Eagerly,
        Diary(now(), "", Weather.Default.ordinal, Weather.Default.defaultText, null)
    )

    private val _state = MutableStateFlow<UIState<Long>>(UIState.Loading)
    val state: StateFlow<UIState<Long>> get() = _state

    fun writeDiary() = viewModelScope.launch {
        Log.d("WriteDiaryViewModel", "writeDiary Click!")
        _state.value = UIState.Loading
        runCatching {
            writeDiaryUseCase.invoke(diary.value)
        }.onSuccess {
            _state.value = UIState.Success(it)
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
