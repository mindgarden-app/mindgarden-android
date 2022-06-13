package com.mindgarden.mindgarden.presentation.diary.read

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.mindgarden.mindgarden.R
import com.mindgarden.mindgarden.databinding.FragmentReadDiaryBinding
import com.mindgarden.mindgarden.presentation.util.common.navigation.NavigationFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ReadDiaryFragment :
    NavigationFragment<ReadDiaryViewModel, FragmentReadDiaryBinding>(R.layout.fragment_read_diary) {

    private val args: ReadDiaryFragmentArgs by navArgs()

    @Inject
    lateinit var readDiaryViewModelFactory: ReadDiaryViewModel.ReadDiaryViewModelFactory

    override val viewModel: ReadDiaryViewModel by viewModels {
        ReadDiaryViewModel.provideFactory(readDiaryViewModelFactory, args.diary)
    }

    override fun setViewModel() {
        binding.vm = viewModel
    }
}
