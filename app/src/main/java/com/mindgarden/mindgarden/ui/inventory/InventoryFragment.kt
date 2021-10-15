package com.mindgarden.mindgarden.ui.inventory

import android.content.res.TypedArray
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.mindgarden.mindgarden.R
import com.mindgarden.mindgarden.databinding.FragmentInventoryBinding
import com.mindgarden.mindgarden.ui.inventory.adapter.garden.GardenAdapter
import com.mindgarden.mindgarden.ui.inventory.adapter.garden.GardenViewHolder
import com.mindgarden.mindgarden.ui.inventory.model.GardenType
import com.mindgarden.mindgarden.ui.inventory.model.InventoryMind
import com.mindgarden.mindgarden.ui.util.base.BaseFragment
import com.mindgarden.mindgarden.util.ext.getGardenDate
import com.mindgarden.mindgarden.util.ext.getLocationArray
import com.mindgarden.mindgarden.util.ext.getTreeResArray
import com.mindgarden.mindgarden.util.ext.now
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.kotlin.addTo
import java.time.LocalDateTime
import javax.inject.Inject

@AndroidEntryPoint
class InventoryFragment:
    BaseFragment<InventoryViewModel, FragmentInventoryBinding>(R.layout.fragment_inventory),
    GardenViewHolder.GardenListener
{

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

    private val gardenAdapter by lazy { GardenAdapter(this) }

    override fun onChange(item: InventoryMind, treeIdx: Int) {
        val itemIdx = gardenAdapter.currentList.indexOf(item)
        with(gardenAdapter.currentList[itemIdx]) {
            type = GardenType.PROGRESS
            this.treeIdx = treeIdx
        }
        gardenAdapter.submitList(gardenAdapter.currentList)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvGarden.apply {
            adapter = gardenAdapter
            itemAnimator = null
        }

        viewModel.garden.observe(viewLifecycleOwner, ::observeGarden)
        viewModel.initInventoryTrees()
        viewModel.initGarden()
        plantTree()
    }

    private fun observeGarden(list: List<InventoryMind>?) {
        gardenAdapter.submitList(list)
    }

    private fun plantTree() {
        binding.btnPlant.setOnClickListener {
            gardenAdapter.currentList.filter { it.type == GardenType.PROGRESS }.forEach {
                Log.d("Inventory Fragment","plant tree filter $it")
                viewModel.plant(it).subscribe(
                    {
                        Toast.makeText(requireContext(), "success plant tree", Toast.LENGTH_SHORT).show()
                    },
                    {
                        Toast.makeText(requireContext(), "fail plant tree: ${it.message}", Toast.LENGTH_SHORT).show()
                    }
                ).addTo(compositeDisposable)
            }
        }
    }
}

