package com.mindgarden.mindgarden.ui.inventory

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.mindgarden.mindgarden.R
import com.mindgarden.mindgarden.data.viewmodelFactory.GardenViewModelFactory
import com.mindgarden.mindgarden.databinding.FragmentInventoryBinding
import com.mindgarden.mindgarden.util.base.BaseFragment
import com.mindgarden.mindgarden.util.ext.getLocationArray
import com.mindgarden.mindgarden.util.ext.getTreeResArray

class InventoryFragment:
    BaseFragment<InventoryViewModel, FragmentInventoryBinding>(R.layout.fragment_inventory) {

    override val viewModel: InventoryViewModel by viewModels {
        GardenViewModelFactory.getInstance(requireActivity().application)
    }

    override fun setViewModel() {
        binding.vm = viewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initInventoryTrees()
        initInventoryGarden()
    }

    private fun initInventoryTrees() {
        val treeResArray = resources.getTreeResArray()
        viewModel.setTrees(treeResArray)
        treeResArray.recycle()
    }

    private fun initInventoryGarden() {
        val gardenLocationResArray = resources.getLocationArray()
        viewModel.initGarden(gardenLocationResArray)
    }
}