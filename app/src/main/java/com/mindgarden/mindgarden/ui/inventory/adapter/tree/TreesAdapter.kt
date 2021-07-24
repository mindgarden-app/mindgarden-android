package com.mindgarden.mindgarden.ui.inventory.adapter.tree

import android.content.ClipData
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Point
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.mindgarden.mindgarden.R
import com.mindgarden.mindgarden.databinding.ItemInventoryBinding
import com.mindgarden.mindgarden.ui.inventory.adapter.util.DragCallback.Companion.LABEL_TREE_RES_ID
import com.mindgarden.mindgarden.ui.inventory.model.InventoryTree
import com.mindgarden.mindgarden.util.base.BaseViewHolder
import com.mindgarden.mindgarden.util.ext.getTreeResArray

class TreesAdapter(val trees: List<InventoryTree>): RecyclerView.Adapter<TreesAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(trees[position])
    }

    override fun getItemCount() = trees.size

    class ViewHolder(viewGroup: ViewGroup)
        : BaseViewHolder<ItemInventoryBinding, InventoryTree>(viewGroup, R.layout.item_inventory){

        init {
            binding.inventoryTreeContainer.setOnLongClickListener { v ->
                val dragData = ClipData.newPlainText(LABEL_TREE_RES_ID, binding.tree?.treeDrawableRes.toString())
                val shadow = MyDragShadowBuilder(v, binding.tree?.treeDrawableRes)
                v.startDragAndDrop(dragData, shadow, null, 0)
            }
        }

        override fun bind(item: InventoryTree) {
            binding.tree = item
            binding.executePendingBindings()
        }

    }

    private class MyDragShadowBuilder(v: View, treeResId: Int?) : View.DragShadowBuilder(v) {
        private val shadow = if (treeResId == null) ColorDrawable(Color.LTGRAY)
            else ContextCompat.getDrawable(v.context, treeResId)

        override fun onProvideShadowMetrics(size: Point, touch: Point) {
            val width: Int = (view.width / 1.0F).toInt()
            val height: Int = (view.height / 1.0F).toInt()

            shadow?.setBounds(0, 0, width, height)
            size.set(width, height)
            touch.set(width / 2, height / 2)
        }
        override fun onDrawShadow(canvas: Canvas) {
            shadow?.draw(canvas)
        }
    }
}