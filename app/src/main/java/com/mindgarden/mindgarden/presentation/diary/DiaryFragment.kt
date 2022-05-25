package com.mindgarden.mindgarden.presentation.diary

import androidx.fragment.app.viewModels
import com.mindgarden.mindgarden.R
import com.mindgarden.mindgarden.databinding.FragmentDiaryBinding
import com.mindgarden.mindgarden.presentation.util.common.navigation.NavigationFragment
import dagger.hilt.android.AndroidEntryPoint

/**
 * Mode : write, view
 */
@AndroidEntryPoint
class DiaryFragment : NavigationFragment<DiaryViewModel, FragmentDiaryBinding>(R.layout.fragment_diary) {

    override val viewModel: DiaryViewModel by viewModels()

    override fun setViewModel() {
        binding.vm = viewModel
    }
}