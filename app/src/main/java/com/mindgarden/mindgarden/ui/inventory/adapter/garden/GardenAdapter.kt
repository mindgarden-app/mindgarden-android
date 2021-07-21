package com.mindgarden.mindgarden.ui.inventory.adapter.garden

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mindgarden.mindgarden.R
import com.mindgarden.mindgarden.ui.inventory.model.GardenType
import com.mindgarden.mindgarden.ui.inventory.model.InventoryGarden


class GardenAdapter(private val items: List<InventoryGarden>):
    ListAdapter<InventoryGarden, RecyclerView.ViewHolder>(GardenDiffUtil()){

    // TODO("Exist, progress type")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            R.layout.item_garden -> GardenViewHolder(parent)
            R.layout.item_river -> RiverViewHolder(parent)
            else -> GardenViewHolder(parent)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
           is GardenViewHolder -> holder.bind(items[position])
           is RiverViewHolder -> holder.bind(items[position])
        }
    }

    override fun getItemCount() = items.count()

    // TODO("Exist, progress type")
    override fun getItemViewType(position: Int) = when(items[position].type) {
        GardenType.EMPTY -> R.layout.item_garden
        GardenType.EXIST -> R.layout.item_garden
        GardenType.PROGRESS -> R.layout.item_garden
        GardenType.RIVER -> R.layout.item_river
    }

    class GardenDiffUtil : DiffUtil.ItemCallback<InventoryGarden>() {
        override fun areItemsTheSame(oldItem: InventoryGarden, newItem: InventoryGarden): Boolean {
            return oldItem.location == newItem.location
        }

        override fun areContentsTheSame(
            oldItem: InventoryGarden,
            newItem: InventoryGarden
        ): Boolean {
            return oldItem == newItem
        }
    }
}