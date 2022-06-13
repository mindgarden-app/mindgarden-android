package com.mindgarden.mindgarden.presentation.diary.read

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.widget.ViewPager2
import com.mindgarden.mindgarden.R
import com.mindgarden.mindgarden.databinding.FragmentDiaryImageBinding
import com.mindgarden.mindgarden.presentation.util.common.navigation.NavigationFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DiaryImageFragment :
    NavigationFragment<DiaryImageViewModel, FragmentDiaryImageBinding>(R.layout.fragment_diary_image) {

    private val args: DiaryImageFragmentArgs by navArgs()

    @Inject
    lateinit var diaryImageViewModelFactory: DiaryImageViewModel.DiaryImageViewModelFactory

    override val viewModel: DiaryImageViewModel by viewModels {
        DiaryImageViewModel.provideFactory(diaryImageViewModelFactory, args.diaryImages.size)
    }

    override fun setViewModel() {
        binding.vm = viewModel
    }

    private val adapter: DiaryImagesAdapter by lazy {
        DiaryImagesAdapter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.vpImageFragment.adapter = adapter
        adapter.submitList(args.diaryImages.toList())

        binding.vpImageFragment.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                viewModel.onPageChange(position+1)
            }
        })
    }

}