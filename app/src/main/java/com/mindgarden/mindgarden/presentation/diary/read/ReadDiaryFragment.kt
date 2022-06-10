package com.mindgarden.mindgarden.presentation.diary.read

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.mindgarden.mindgarden.R
import com.mindgarden.mindgarden.databinding.FragmentReadDiaryBinding
import com.mindgarden.mindgarden.presentation.util.common.navigation.NavigationFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

// TODO : 이미지뷰 UI 정해지면 다시 작업
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

//    private val rvImagesAdapter : ReadDiaryImagesAdapter by lazy {
//        ReadDiaryImagesAdapter()
//    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        binding.rvImagesReadDiary.adapter = rvImagesAdapter
//        rvImagesAdapter.submitList(args.diary.img)
    }
}
