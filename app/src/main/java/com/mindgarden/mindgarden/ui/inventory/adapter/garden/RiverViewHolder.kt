package com.mindgarden.mindgarden.ui.inventory.adapter.garden

import android.view.ViewGroup
import com.mindgarden.mindgarden.R
import com.mindgarden.mindgarden.databinding.ItemRiverBinding
import com.mindgarden.mindgarden.ui.inventory.model.InventoryGarden
import com.mindgarden.mindgarden.util.base.BaseViewHolder

class RiverViewHolder(viewGroup: ViewGroup):
    BaseViewHolder<ItemRiverBinding, InventoryGarden>(viewGroup, R.layout.item_river) {

    override fun bind(item: InventoryGarden) {
        binding.garden = item
        binding.executePendingBindings()
    }
}