package com.mindgarden.mindgarden.presentation.diarylist

import android.app.Application
import android.util.Log
import androidx.lifecycle.viewModelScope
import com.mindgarden.mindgarden.R
import com.mindgarden.mindgarden.data.db.entity.Diary
import com.mindgarden.mindgarden.domain.usecase.diary.DeleteDiaryUseCase
import com.mindgarden.mindgarden.domain.usecase.diary.LoadDiaryListUseCase
import com.mindgarden.mindgarden.presentation.util.common.*
import com.mindgarden.mindgarden.presentation.util.common.navigation.NavigationViewModel
import com.mindgarden.mindgarden.util.Result
import com.mindgarden.mindgarden.util.ext.now
import com.mindgarden.mindgarden.util.ext.toGardenDate
import com.mindgarden.mindgarden.util.ext.toStringOfPattern
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class DiaryListViewModel @Inject constructor(
    private val loadDiaryListUseCase: LoadDiaryListUseCase,
    private val deleteDiaryUseCase: DeleteDiaryUseCase,
    private val application: Application
) : NavigationViewModel() {

    var today = now().toGardenDate()

    private val _date = MutableStateFlow(now().toGardenDate())
    val date: StateFlow<LocalDateTime> get() = _date

    fun setToolbarDate(date: LocalDateTime) {
        _date.value = date
        loadDiary()
    }

    fun getToolbarDate(): LocalDateTime {
        return _date.value;
    }

    private var isASC: Boolean = false

    private val _diary = MutableStateFlow<List<Diary>>(emptyList())
    val diary: StateFlow<List<Diary>> = _diary

    fun deleteDiary(diary: Diary) = viewModelScope.launch {
        deleteDiaryUseCase.invoke(diary)
        Log.e("삭제 완료", "")
    }

    init {
        loadDiary()
    }

    private var loadDiaryJob: Job? = null

    private fun loadDiary() {
        loadDiaryJob?.cancel()
        loadDiaryJob = viewModelScope.launch {
            val date =
                _date.value.toStringOfPattern(application.getString(R.string.pattern_calendar))

            loadDiaryListUseCase.invoke(date, isASC).collect { result ->
                when (result) {
                    is Result.Error -> {
                        // show error
                    }
                    is Result.Success -> {
                        _diary.value = result.data
                    }
                }
            }
        }
    }

    fun goReadDiaryFragment(diary: Diary) {
        navigate(DiaryListFragmentDirections.actionDiaryListFragmentToReadDiaryFragment(diary))
    }

    val toolbarListener = object : MainToolbarListener {
        override val toolbarData: MainToolbar
            get() = MainToolbar.DiaryListToolbar().copy(
                //title = getToolbarDate().toStringOfPattern(application.getString(R.string.pattern_toolbar_diary_list))
            )

        override fun leftButtonClick() {
            setToolbarDate(getToolbarDate().minusMonths(1))
        }

        override fun rightButtonClick() {
            if (!getToolbarDate().toStringOfPattern(application.getString(R.string.pattern_calendar)).equals(
                    today.toStringOfPattern(application.getString(R.string.pattern_calendar))
            )) {
                setToolbarDate(getToolbarDate().plusMonths(1))
            }
        }

        override fun sortButtonClick() {
            isASC = !isASC
            loadDiary()
        }
    }
}