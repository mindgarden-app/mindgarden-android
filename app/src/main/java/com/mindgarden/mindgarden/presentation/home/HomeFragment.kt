package com.mindgarden.mindgarden.presentation.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.mindgarden.mindgarden.R
import com.mindgarden.mindgarden.databinding.FragmentHomeBinding
import com.mindgarden.mindgarden.presentation.util.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<HomeViewModel, FragmentHomeBinding>(R.layout.fragment_home) {

    override val viewModel: HomeViewModel by viewModels()

    override fun setViewModel() {
        binding.vm = viewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnGoInventory.setOnClickListener {
            findNavController().navigate(R.id.action_dest_home_to_dest_inventory)
        }
    }

}