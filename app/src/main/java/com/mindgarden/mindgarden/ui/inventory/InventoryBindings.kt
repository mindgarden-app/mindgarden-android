package com.mindgarden.mindgarden.ui.inventory

import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mindgarden.mindgarden.R
import com.mindgarden.mindgarden.ui.inventory.adapter.garden.GardenAdapter
import com.mindgarden.mindgarden.ui.inventory.adapter.tree.TreesAdapter
import com.mindgarden.mindgarden.ui.inventory.model.GardenType
import com.mindgarden.mindgarden.ui.inventory.model.InventoryGarden
import com.mindgarden.mindgarden.ui.inventory.model.InventoryTree

@BindingAdapter("bind:setImageRes")
fun setImageRes(iv: ImageView, @DrawableRes res: Int?) {
    res?.let {
        iv.setImageResource(it)
    }
}

@BindingAdapter("bind:treeAdapter")
fun setTreeAdapter(rv: RecyclerView, items: List<InventoryTree>) {
    rv.adapter = TreesAdapter(items)
}

@BindingAdapter("bind:gardenAdapter")
fun setGardenAdapter(rv: RecyclerView, items: List<InventoryGarden>) {
    rv.adapter = GardenAdapter(items)
}

@BindingAdapter("bind:gardenType")
fun setGardenBackground(iv: ImageView, type: GardenType) {
    when (type) {
        GardenType.EMPTY -> iv.setBackgroundResource(R.drawable.border_garden)
        GardenType.EXIST -> iv.setBackgroundResource(R.drawable.border_garden)
        GardenType.PROGRESS -> iv.setBackgroundResource(R.drawable.border_garden)
        GardenType.RIVER -> iv.setBackgroundResource(R.drawable.background_river)
    }
}