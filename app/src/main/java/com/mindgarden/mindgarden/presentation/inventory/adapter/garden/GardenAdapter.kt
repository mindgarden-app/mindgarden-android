package com.mindgarden.mindgarden.presentation.inventory.adapter.garden

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mindgarden.mindgarden.R
import com.mindgarden.mindgarden.presentation.inventory.model.GardenType
import com.mindgarden.mindgarden.presentation.inventory.model.InventoryMind

class GardenAdapter(private val listener: GardenViewHolder.GardenListener) :
    ListAdapter<InventoryMind, RecyclerView.ViewHolder>(GardenDiffUtil()){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            R.layout.item_garden -> GardenViewHolder(parent, listener)
            R.layout.item_planted_garden -> PlantedGardenViewHolder(parent)
            R.layout.item_river -> RiverViewHolder(parent)
            else -> GardenViewHolder(parent, listener)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is GardenViewHolder -> holder.bind(getItem(position))
            is PlantedGardenViewHolder -> holder.bind(getItem(position))
            is RiverViewHolder -> holder.bind(getItem(position))
        }
    }

    override fun getItemViewType(position: Int) = when(getItem(position).type) {
        GardenType.EMPTY -> R.layout.item_garden
        GardenType.PLANTED -> R.layout.item_planted_garden
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