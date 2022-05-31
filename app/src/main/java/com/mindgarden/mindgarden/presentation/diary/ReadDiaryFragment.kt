package com.mindgarden.mindgarden.presentation.diary

import android.util.Log
import androidx.fragment.app.viewModels
import com.mindgarden.mindgarden.R
import com.mindgarden.mindgarden.data.db.entity.Diary
import com.mindgarden.mindgarden.databinding.FragmentReadDiaryBinding
import com.mindgarden.mindgarden.presentation.util.common.UIState
import com.mindgarden.mindgarden.presentation.util.common.navigation.NavigationFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ReadDiaryFragment :
    NavigationFragment<ReadDiaryViewModel, FragmentReadDiaryBinding>(R.layout.fragment_read_diary) {

    override val viewModel: ReadDiaryViewModel by viewModels()

    override fun setViewModel() {
        binding.vm = viewModel
    }

    override fun observeData() {
        // todo : DiaryList 에서 idx 받기
        viewModel.loadDiary(1)
    }

}