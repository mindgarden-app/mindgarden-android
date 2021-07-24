package com.mindgarden.mindgarden.ui.inventory.adapter.garden

import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.util.Log
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import com.mindgarden.mindgarden.R
import com.mindgarden.mindgarden.databinding.ItemGardenBinding
import com.mindgarden.mindgarden.ui.inventory.adapter.util.DragCallback
import com.mindgarden.mindgarden.ui.inventory.model.InventoryGarden
import com.mindgarden.mindgarden.util.base.BaseViewHolder
import com.mindgarden.mindgarden.util.bindingAdapter.setImageRes
import com.mindgarden.mindgarden.util.ext.getColorResId
import com.mindgarden.mindgarden.util.ext.overrideColor

class GardenViewHolder(viewGroup: ViewGroup): BaseViewHolder<ItemGardenBinding, InventoryGarden>(
    viewGroup, R.layout.item_garden
){
    init {
        with(binding.ivGarden) {
            val drawable = background as Drawable
            setBackground(drawable, R.color.white)

            setOnDragListener(DragCallback(object : DragCallback.OnDragListener {
                override fun onDragEntered() {
                    val background = background as Drawable
                    setBackground(background, R.color.background_garden_selected_inventory)
                }

                override fun onDragExited() {
                    val background = background as Drawable
                    setBackground(background, R.color.white)
                }

                override fun onDrop(@DrawableRes treeResId: Int) {
                    Log.d("GardenAdapter", "itemId: ${treeResId}, to: ${binding.garden?.location}")
                    val background = background as Drawable
                    setBackground(background, R.color.white)
                    setImageRes(this@with, treeResId)
                }
            }))
        }
    }

    private fun setBackground(drawable: Drawable, colorId: Int) {
        val color = context.getColor(colorId)
        drawable.overrideColor(color)
    }

    override fun bind(item: InventoryGarden) {
        binding.garden = item
        binding.executePendingBindings()
    }
}
