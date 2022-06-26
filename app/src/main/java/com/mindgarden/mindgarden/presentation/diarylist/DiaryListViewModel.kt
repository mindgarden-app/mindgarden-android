package com.mindgarden.mindgarden.presentation.diarylist

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mindgarden.mindgarden.data.db.entity.Diary
import com.mindgarden.mindgarden.domain.usecase.diary.DeleteDiaryUseCase
import com.mindgarden.mindgarden.domain.usecase.diary.LoadDiaryListUseCase
import com.mindgarden.mindgarden.presentation.util.common.*
import com.mindgarden.mindgarden.util.ext.now
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class DiaryListViewModel @Inject constructor(
    private val loadDiaryListUseCase: LoadDiaryListUseCase,
    private val deleteDiaryUseCase: DeleteDiaryUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<UIState<List<Diary>>>(UIState.Loading)
    val state: StateFlow<UIState<List<Diary>>> get() = _state

    private val _date = MutableStateFlow<LocalDateTime>(now())
    val date: StateFlow<LocalDateTime> get() = _date

    fun setToolbarDate(date: LocalDateTime) {
        _date.value = date
    }

    fun getToolbarDate(): LocalDateTime {
        return _date.value;
    }

    fun loadDiaryList(date: String, isASC: Boolean) = viewModelScope.launch {
        loadDiaryListUseCase.invoke(date,isASC)
                .onStart {
                    Log.d("DiaryListViewModel", "loadDiaryList: loading, date: $date")
                   _state.value = UIState.Loading
                }.collect { result ->
                    when (result) {
                        is com.mindgarden.mindgarden.util.Result.Error -> {
                            _state.value = UIState.Error(result.throwable as Error)
                        }
                        is com.mindgarden.mindgarden.util.Result.Success -> {
                            if (isASC) {
                                result.data.sortedBy { it -> it.date }
                            } else {
                                result.data.sortedByDescending { it -> it.date }
                            }

                            _state.value = UIState.Success(result.data)
                            Log.d("DiaryListViewModel", "loadDiaryList: ${result.data.size}")
                            result.data.forEach {
                                Log.d("DiaryListViewModel", "loadDiaryList: $it, ${it.weather.customText}")
                            }
                        }
                    }
                }
        }

    fun deleteDiary(idx: Long) = viewModelScope.launch {
        deleteDiaryUseCase.invoke(idx)
        Log.e("삭제 완료", "")
    }

    val toolbarListener = object : MainToolbarListener {
        override val toolbarData: MainToolbar
            get() = MainToolbar.DiaryListToolbar().copy(
                //title = getToolDate().toStringOfPattern(application.getString(R.string.pattern_toolbar_diary_list))
                //title = _date.value.toStringOfPattern(application.getString(R.string.pattern_toolbar_diary_list))
            )

        override fun leftButtonClick() {
            setToolbarDate(getToolbarDate().minusMonths(1))
        }

        override fun rightButtonClick() {
            setToolbarDate(getToolbarDate().plusMonths(1))
        }

        override fun sortButtonClick() {
        }
    }
}