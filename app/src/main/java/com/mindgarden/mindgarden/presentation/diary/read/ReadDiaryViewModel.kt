package com.mindgarden.mindgarden.presentation.diary.read

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mindgarden.mindgarden.R
import com.mindgarden.mindgarden.data.db.entity.Diary
import com.mindgarden.mindgarden.presentation.util.common.GardenToolbar
import com.mindgarden.mindgarden.presentation.util.common.GardenToolbarListener
import com.mindgarden.mindgarden.presentation.util.common.navigation.NavigationViewModel
import com.mindgarden.mindgarden.util.ext.toStringOfPattern
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class ReadDiaryViewModel @AssistedInject constructor(
    private val application: Application,
    @Assisted val diary: Diary,
) : NavigationViewModel() {

    @AssistedFactory
    interface ReadDiaryViewModelFactory {
        fun create(diary: Diary): ReadDiaryViewModel
    }

    companion object {
        @Suppress("UNCHECKED_CAST")
        fun provideFactory(
            readDiaryViewModelFactory: ReadDiaryViewModelFactory,
            diary: Diary
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return readDiaryViewModelFactory.create(diary) as T
            }
        }
    }

    val toolbarListener = object : GardenToolbarListener {
        override val toolbarData: GardenToolbar
            get() = GardenToolbar.ReadDiaryToolbar().copy(
                title = diary.date.toStringOfPattern(application.getString(R.string.pattern_toolbar_diary))
            )

        override fun leftButtonClick() {
            navigateBack()
        }

        override fun rightButtonClick() {
            navigate(ReadDiaryFragmentDirections.actionReadDiaryFragmentToWriteDiaryFragment(diary))
        }
    }
}