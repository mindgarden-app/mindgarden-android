package com.mindgarden.mindgarden.ui.inventory

import android.content.res.TypedArray
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.mindgarden.mindgarden.R
import com.mindgarden.mindgarden.databinding.FragmentInventoryBinding
import com.mindgarden.mindgarden.ui.inventory.adapter.garden.GardenAdapter
import com.mindgarden.mindgarden.ui.inventory.model.InventoryMind
import com.mindgarden.mindgarden.ui.util.base.BaseFragment
import com.mindgarden.mindgarden.util.ext.getGardenDate
import com.mindgarden.mindgarden.util.ext.getLocationArray
import com.mindgarden.mindgarden.util.ext.getTreeResArray
import com.mindgarden.mindgarden.util.ext.now
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDateTime
import javax.inject.Inject

@AndroidEntryPoint
class InventoryFragment:
    BaseFragment<InventoryViewModel, FragmentInventoryBinding>(R.layout.fragment_inventory) {

    @Inject
    lateinit var inventoryViewModelFactory: InventoryViewModel.AssistedFactory

    private val inventoryInitData = object : InventoryViewModel.InventoryData {
        override val treeResArray: TypedArray
            get() = resources.getTreeResArray()
        override val locationResArray: IntArray
            get() = resources.getLocationArray()
        // TODO : Home 에서 넘겨 받은 gardenDate 값 필요
        override val gardenDate: LocalDateTime
            get() = now().getGardenDate()
    }

    override val viewModel: InventoryViewModel by viewModels {
        InventoryViewModel.provideFactory(
            inventoryViewModelFactory,
            inventoryInitData
        )
    }

    override fun setViewModel() {
        binding.vm = viewModel
    }

    private val gardenAdapter by lazy { GardenAdapter() }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvGarden.adapter = gardenAdapter
        viewModel.garden.observe(viewLifecycleOwner, ::observeGarden)
        viewModel.initInventoryTrees()
        viewModel.initGarden()
    }

    private fun observeGarden(list: List<InventoryMind>?) {
        gardenAdapter.submitList(list)
    }

    fun plantTree() {
        binding.btnPlant.setOnClickListener {

        }
    }
}

