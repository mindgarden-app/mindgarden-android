package com.mindgarden.mindgarden.ui.inventory.adapter.garden

import android.view.ViewGroup
import com.mindgarden.mindgarden.R
import com.mindgarden.mindgarden.databinding.ItemPlantedGardenBinding
import com.mindgarden.mindgarden.ui.inventory.model.InventoryMind
import com.mindgarden.mindgarden.util.base.BaseViewHolder

class PlantedGardenViewHolder(viewGroup: ViewGroup) :
    BaseViewHolder<ItemPlantedGardenBinding, InventoryMind>(viewGroup, R.layout.item_planted_garden) {

    override fun bind(item: InventoryMind) {
        binding.garden = item
        binding.executePendingBindings()
    }
}