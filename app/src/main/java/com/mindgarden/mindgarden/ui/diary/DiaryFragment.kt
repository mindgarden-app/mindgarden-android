package com.mindgarden.mindgarden.ui.diary

import androidx.fragment.app.viewModels
import com.mindgarden.mindgarden.R
import com.mindgarden.mindgarden.databinding.FragmentDiaryBinding
import com.mindgarden.mindgarden.ui.util.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DiaryFragment : BaseFragment<DiaryViewModel, FragmentDiaryBinding>(R.layout.fragment_diary) {

    override val viewModel: DiaryViewModel by viewModels()

    override fun setViewModel() {
        binding.vm = viewModel
    }

    override fun observeData() {
        viewModel.showProgress()
    }
}