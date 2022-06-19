package com.mindgarden.mindgarden.presentation.diarylist

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mindgarden.mindgarden.data.db.entity.Diary
import com.mindgarden.mindgarden.domain.usecase.diary.DeleteDiaryUseCase
import com.mindgarden.mindgarden.domain.usecase.diary.LoadDiaryListUseCase
import com.mindgarden.mindgarden.presentation.util.base.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DiaryListViewModel @Inject constructor(
    private val loadDiaryListUseCase: LoadDiaryListUseCase,
    private val deleteDiaryUseCase: DeleteDiaryUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<UIState<List<Diary>>>(UIState.Loading)
    val state: StateFlow<UIState<List<Diary>>> get() = _state

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
                            _state.value = UIState.Success(result.data)
                            Log.d("DiaryListViewModel", "loadDiaryList: ${result.data.size}")
                        }
                    }
                }
        }

    fun deleteDiary(idx: Long) = viewModelScope.launch {
        deleteDiaryUseCase.invoke(idx)
        Log.e("삭제 완료", "")
    }
}