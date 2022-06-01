package com.mindgarden.mindgarden.presentation.diary.read

import androidx.fragment.app.viewModels
import com.mindgarden.mindgarden.R
import com.mindgarden.mindgarden.databinding.FragmentReadDiaryBinding
import com.mindgarden.mindgarden.presentation.util.common.navigation.NavigationFragment
import dagger.hilt.android.AndroidEntryPoint

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