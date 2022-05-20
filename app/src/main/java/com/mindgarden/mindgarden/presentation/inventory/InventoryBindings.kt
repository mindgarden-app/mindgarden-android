package com.mindgarden.mindgarden.presentation.inventory

import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mindgarden.mindgarden.R
import com.mindgarden.mindgarden.presentation.inventory.adapter.tree.TreesAdapter
import com.mindgarden.mindgarden.presentation.inventory.model.GardenType
import com.mindgarden.mindgarden.presentation.inventory.model.InventoryTree

@BindingAdapter("setImageRes")
fun setImageRes(iv: ImageView, @DrawableRes res: Int?) {
    res?.let {
        iv.setImageResource(it)
    }
}

@BindingAdapter("treeAdapter")
fun setTreeAdapter(rv: RecyclerView, items: List<InventoryTree>) {
    rv.adapter = TreesAdapter(items)
}

@BindingAdapter("gardenType")
fun setGardenBackground(iv: ImageView, type: GardenType) {
    when (type) {
        GardenType.EMPTY -> iv.setBackgroundResource(R.drawable.border_garden)
        GardenType.PLANTED -> iv.setBackgroundResource(R.drawable.border_garden)
        GardenType.PROGRESS -> iv.setBackgroundResource(R.drawable.border_garden)
        GardenType.RIVER -> iv.setBackgroundResource(R.drawable.background_river)
    }
}