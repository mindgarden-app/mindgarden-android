package com.mindgarden.mindgarden.presentation.inventory.adapter.garden

import android.view.ViewGroup
import com.mindgarden.mindgarden.R
import com.mindgarden.mindgarden.databinding.ItemRiverBinding
import com.mindgarden.mindgarden.presentation.inventory.model.InventoryMind
import com.mindgarden.mindgarden.util.base.BaseViewHolder

class RiverViewHolder(viewGroup: ViewGroup):
    BaseViewHolder<ItemRiverBinding, InventoryMind>(viewGroup, R.layout.item_river) {

    override fun bind(item: InventoryMind) {
        binding.garden = item
        binding.executePendingBindings()
    }
}