package com.mindgarden.mindgarden.ui.inventory.adapter.garden

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mindgarden.mindgarden.R
import com.mindgarden.mindgarden.ui.inventory.model.GardenType
import com.mindgarden.mindgarden.ui.inventory.model.InventoryMind


class GardenAdapter: ListAdapter<InventoryMind, RecyclerView.ViewHolder>(GardenDiffUtil()){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            R.layout.item_garden -> GardenViewHolder(parent)
            R.layout.item_river -> RiverViewHolder(parent)
            else -> GardenViewHolder(parent)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
           is GardenViewHolder -> holder.bind(getItem(position))
           is RiverViewHolder -> holder.bind(getItem(position))
        }
    }

    override fun getItemViewType(position: Int) = when(getItem(position).type) {
        GardenType.EMPTY -> R.layout.item_garden
        GardenType.EXIST -> R.layout.item_garden
        GardenType.PROGRESS -> R.layout.item_garden
        GardenType.RIVER -> R.layout.item_river
    }

    class GardenDiffUtil : DiffUtil.ItemCallback<InventoryMind>() {
        override fun areItemsTheSame(oldItem: InventoryMind, newItem: InventoryMind): Boolean {
            return oldItem.idx == newItem.idx
        }

        override fun areContentsTheSame(
            oldItem: InventoryMind,
            newItem: InventoryMind
        ): Boolean {
            return oldItem.location == newItem.location && oldItem.treeIdx == newItem.treeIdx
        }
    }
}