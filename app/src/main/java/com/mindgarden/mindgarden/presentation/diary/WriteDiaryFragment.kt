package com.mindgarden.mindgarden.presentation.diary

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.NavArgs
import androidx.navigation.fragment.navArgs
import com.mindgarden.mindgarden.R
import com.mindgarden.mindgarden.data.db.entity.Diary
import com.mindgarden.mindgarden.databinding.FragmentWriteDiaryBinding
import com.mindgarden.mindgarden.presentation.util.common.navigation.NavigationFragment
import dagger.hilt.android.AndroidEntryPoint

/**
 * Mode : write, view
 */

@AndroidEntryPoint
class WriteDiaryFragment :
    NavigationFragment<WriteDiaryViewModel, FragmentWriteDiaryBinding>(R.layout.fragment_write_diary) {
    private val args: WriteDiaryFragmentArgs by navArgs()
    override val viewModel: WriteDiaryViewModel by viewModels()

    override fun setViewModel() {
        binding.vm = viewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.currentDiary = args.diary
    }

    companion object {
        const val SAVED_DIARY = "saved_state_handle_diary"
    }
}