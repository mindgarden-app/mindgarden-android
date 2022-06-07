package com.mindgarden.mindgarden.presentation.diary.write

import android.graphics.Rect
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import com.mindgarden.mindgarden.R
import com.mindgarden.mindgarden.databinding.FragmentWriteDiaryBinding
import com.mindgarden.mindgarden.presentation.diary.weather.Weather
import com.mindgarden.mindgarden.presentation.diary.weather.WeatherFragment.Companion.WEATHER
import com.mindgarden.mindgarden.presentation.util.common.navigation.NavigationFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WriteDiaryFragment :
    NavigationFragment<WriteDiaryViewModel, FragmentWriteDiaryBinding>(R.layout.fragment_write_diary) {
    private val args: WriteDiaryFragmentArgs by navArgs()
    override val viewModel: WriteDiaryViewModel by viewModels()

    private val selectImageAdapter by lazy {
        SelectImageAdapter(
            { onRemoveImageButtonClicked(it) },
            { viewModel.setImages(it) }
        )
    }

    override fun setViewModel() {
        binding.vm = viewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.currentDiary = args.diary

        initRecyclerview()
        setWeather()
        onAddPhotoClicked()
    }

    inner class ItemDecoration : RecyclerView.ItemDecoration() {
        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {
            context?.let {
                outRect.right =
                    it.resources.getDimensionPixelSize(R.dimen.fragment_write_diary_item_right_space)
                outRect.bottom =
                    it.resources.getDimensionPixelSize(R.dimen.fragment_write_diary_item_bottom_space)
                outRect.top =
                    it.resources.getDimensionPixelSize(R.dimen.fragment_write_diary_item_top_space)
            }
        }
    }

    private fun initRecyclerview() {
        with(binding.rvImagesWriteDiary) {
            addItemDecoration(ItemDecoration())
            adapter = selectImageAdapter
        }
    }

    private fun setWeather() {
        navController.currentBackStackEntry?.savedStateHandle?.get<Weather>(WEATHER)?.let {
            viewModel.setWeather(it)
        }
    }

    // gallery
    private val getImages =
        registerForActivityResult(ActivityResultContracts.GetMultipleContents()) {
            it?.let {
                it.map { uri -> uri.toString() }.toList().apply {
                    selectImageAdapter.submitList(this)
                }
            }
        }

    private fun onAddPhotoClicked() {
        binding.btnAddPhoto.setOnClickListener {
            getImages.launch("image/*")
        }
    }

    private fun onRemoveImageButtonClicked(position: Int) {
        selectImageAdapter.removeItem(position)
    }

    companion object {
        const val SAVED_DIARY = "saved_state_handle_diary"
    }
}