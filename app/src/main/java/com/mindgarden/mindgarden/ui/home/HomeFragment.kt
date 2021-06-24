package com.mindgarden.mindgarden.ui.home

import androidx.fragment.app.viewModels
import com.mindgarden.mindgarden.R
import com.mindgarden.mindgarden.data.viewmodelFactory.GardenViewModelFactory
import com.mindgarden.mindgarden.databinding.FragmentHomeBinding
import com.mindgarden.mindgarden.util.base.BaseFragment

class HomeFragment : BaseFragment<HomeViewModel, FragmentHomeBinding>(R.layout.fragment_home) {

    override val viewModel: HomeViewModel by viewModels {
        GardenViewModelFactory.getInstance(requireActivity().application)
    }

    override fun setViewModel() {
        binding.vm = viewModel
    }

}