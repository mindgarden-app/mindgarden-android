package com.mindgarden.mindgarden.ui.inventory

import android.content.res.TypedArray
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mindgarden.mindgarden.data.repository.gardenRepo.GardenRepository
import com.mindgarden.mindgarden.ui.inventory.model.GardenType
import com.mindgarden.mindgarden.ui.inventory.model.InventoryGarden
import com.mindgarden.mindgarden.ui.inventory.model.InventoryTree
import com.mindgarden.mindgarden.ui.util.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class InventoryViewModel @Inject constructor(private val gardenRepository: GardenRepository)
    : BaseViewModel() {
    private val _trees = MutableLiveData<MutableList<InventoryTree>>()
    val trees: LiveData<MutableList<InventoryTree>> get() = _trees

    fun setTrees(treeResArray: TypedArray) {
        val items = mutableListOf<InventoryTree>()
        for(i in 0 until treeResArray.length()) {
            items.add(InventoryTree(i, treeResArray.getResourceId(i, -1)))
        }
        _trees.value = items
    }

    private val _garden = MutableLiveData<MutableList<InventoryGarden>>()
    val garden: LiveData<MutableList<InventoryGarden>> get() = _garden

    fun initGarden(garden: IntArray) {
        val items = mutableListOf<InventoryGarden>()
        // TODO : repository에서 tree data 가져 오기
        for (i in 0..garden.lastIndex) {
            when (garden[i]) {
                13, 19, 18, 24 -> {
                    items.add(InventoryGarden(garden[i], 1, GardenType.RIVER))
                }
                3 -> {   // Test
                    items.add(InventoryGarden(garden[i], 2131230823, GardenType.EXIST))
                }
                else -> {
                    items.add(InventoryGarden(garden[i], null, GardenType.EMPTY))
                }
            }
        }
        _garden.value = items
    }
}