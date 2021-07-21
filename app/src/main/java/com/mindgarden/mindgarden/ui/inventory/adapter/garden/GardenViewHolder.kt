package com.mindgarden.mindgarden.ui.inventory.adapter.garden

import android.graphics.drawable.Drawable
import android.util.Log
import android.view.ViewGroup
import com.mindgarden.mindgarden.R
import com.mindgarden.mindgarden.databinding.ItemGardenBinding
import com.mindgarden.mindgarden.ui.inventory.adapter.util.DragCallback
import com.mindgarden.mindgarden.ui.inventory.model.InventoryGarden
import com.mindgarden.mindgarden.util.base.BaseViewHolder
import com.mindgarden.mindgarden.util.ext.getColorResId
import com.mindgarden.mindgarden.util.ext.overrideColor

class GardenViewHolder(viewGroup: ViewGroup): BaseViewHolder<ItemGardenBinding, InventoryGarden>(
    viewGroup, R.layout.item_garden
){
    init {
        with(binding.ivGarden) {
            setOnDragListener(DragCallback(object : DragCallback.OnDragListener {
                override fun onDragEntered() {
                    val drawable = background as Drawable
                    val colorId = context.getColorResId(R.color.background_garden_selected_inventory)
                    drawable.overrideColor(colorId)
                }

                override fun onDragExited() {
                    val drawable = background as Drawable
                    val colorId = context.getColorResId(R.color.white)
                    drawable.overrideColor(colorId)
                }

                override fun onDrop(imgId: String) {
                    Log.d("GardenAdapter", "imgId: ${imgId}, to: ${text}")
                }
            }))
        }
    }

    override fun bind(item: InventoryGarden) {
        binding.garden = item
        binding.executePendingBindings()
    }
}
