package com.mindgarden.mindgarden.ui.inventory

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.mindgarden.mindgarden.R
import com.mindgarden.mindgarden.databinding.FragmentInventoryBinding
import com.mindgarden.mindgarden.ui.util.base.BaseFragment
import com.mindgarden.mindgarden.util.ext.getLocationArray
import com.mindgarden.mindgarden.util.ext.getTreeResArray
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InventoryFragment:
    BaseFragment<InventoryViewModel, FragmentInventoryBinding>(R.layout.fragment_inventory) {

    override val viewModel: InventoryViewModel by viewModels()

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