package com.mindgarden.mindgarden.presentation.diary

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.mindgarden.mindgarden.data.db.entity.Diary
import com.mindgarden.mindgarden.domain.usecase.diary.LoadDiaryUseCase
import com.mindgarden.mindgarden.presentation.util.common.GardenToolbar
import com.mindgarden.mindgarden.presentation.util.common.GardenToolbarListener
import com.mindgarden.mindgarden.presentation.util.common.UIState
import com.mindgarden.mindgarden.presentation.util.common.navigation.NavigationViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReadDiaryViewModel @Inject constructor(private val loadDiaryUseCase: LoadDiaryUseCase) :
    NavigationViewModel() {

    private val _state = MutableStateFlow<UIState<Diary>>(UIState.Loading)
    val state: StateFlow<UIState<Diary>> get() = _state

    fun loadDiary(diaryId: Long) = viewModelScope.launch {
        loadDiaryUseCase.invoke(diaryId)
            .onStart {
                Log.d("ReadDiaryViewModel", "loadDiaryList: loading, diary idx: $diaryId")
                _state.value = UIState.Loading
            }.collect { result ->
                when (result) {
                    is com.mindgarden.mindgarden.util.Result.Error -> {
                        _state.value = UIState.Error(result.throwable as Error)
                    }
                    is com.mindgarden.mindgarden.util.Result.Success -> {
                        _state.value = UIState.Success(result.data)
                        Log.d("DiaryListViewModel", "diary: ${result.data}")
                    }
                }
            }
    }

    val toolbarListener = object : GardenToolbarListener {
        override val toolbarData: GardenToolbar
            get() = GardenToolbar.ReadDiaryToolbar().copy(title = "21.05.26")

        override fun leftButtonClick() {
            navigateBack()
        }

        override fun rightButtonClick() {
            val stateValue = _state.value
            if (stateValue is UIState.Success<Diary>) {
                navigate(
                    ReadDiaryFragmentDirections.actionReadDiaryFragmentToWriteDiaryFragment(
                        stateValue.data
                    )
                )
            }
        }
    }
}