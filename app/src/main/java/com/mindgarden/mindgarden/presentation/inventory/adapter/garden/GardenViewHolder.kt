package com.mindgarden.mindgarden.presentation.inventory.adapter.garden

import android.graphics.drawable.Drawable
import android.util.Log
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import com.mindgarden.mindgarden.R
import com.mindgarden.mindgarden.databinding.ItemGardenBinding
import com.mindgarden.mindgarden.presentation.inventory.adapter.util.DragCallback
import com.mindgarden.mindgarden.presentation.inventory.model.InventoryMind
import com.mindgarden.mindgarden.presentation.util.setImageRes
import com.mindgarden.mindgarden.util.base.BaseViewHolder
import com.mindgarden.mindgarden.util.ext.overrideColor

class GardenViewHolder(viewGroup: ViewGroup, listener: GardenListener) :
    BaseViewHolder<ItemGardenBinding, InventoryMind>(viewGroup, R.layout.item_garden) {
    init {
        with(binding.ivGarden) {
            val drawable = background as Drawable
            setBackground(drawable, R.color.white)

            setOnDragListener(DragCallback(object : DragCallback.OnDragListener {
                override fun onDragEntered() = Unit

                override fun onDragExited() = Unit

                override fun onDrop(treeId: Int, @DrawableRes treeResId: Int) {
                    Log.d("EmptyGardenViewHolder",
                        "itemId: $treeId resId: $treeResId, to: ${binding.garden?.location}")
                    val background = background as Drawable
                    setBackground(background, R.color.background_garden_selected_inventory)
                    setImageRes(this@with, treeResId)
                    listener.onChange(binding.garden!!, treeId)
                }
            }))
        }
    }

    private fun setBackground(drawable: Drawable, colorId: Int) {
        val color = context.getColor(colorId)
        drawable.overrideColor(color)
    }

    override fun bind(item: InventoryMind) {
        binding.garden = item
        binding.executePendingBindings()
    }

    interface GardenListener {
        fun onChange(item: InventoryMind, treeIdx: Int)
    }
}
