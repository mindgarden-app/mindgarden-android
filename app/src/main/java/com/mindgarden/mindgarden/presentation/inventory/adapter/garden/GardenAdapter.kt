package com.mindgarden.mindgarden.presentation.inventory.adapter.garden

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mindgarden.mindgarden.R
import com.mindgarden.mindgarden.databinding.ItemGardenBinding
import com.mindgarden.mindgarden.databinding.ItemLakeBinding
import com.mindgarden.mindgarden.presentation.inventory.model.GardenType
import com.mindgarden.mindgarden.presentation.inventory.model.InventoryMind

class GardenAdapter(
    initialSelectedPosition: Int,
    val clickEvent: (location: Int) -> Unit,
    val removeEvent: (location: Int) -> Unit
) :
    ListAdapter<InventoryMind, RecyclerView.ViewHolder>(GardenDiffUtil()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            R.layout.item_lake -> LakeViewHolder.from(parent)
            else -> {
                val binding = DataBindingUtil.inflate<ItemGardenBinding>(
                    LayoutInflater.from(parent.context),
                    R.layout.item_garden,
                    parent,
                    false
                )
                GardenViewHolder(binding)
            }
        }
    }

    private var selectedPosition = initialSelectedPosition

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is GardenViewHolder) {
            if (position in currentList.indices) {
                holder.bind(getItem(position), position == selectedPosition)
            }
        }
    }

    override fun getItemViewType(position: Int) = when (getItem(position).type) {
        GardenType.EMPTY, GardenType.PLANTED -> R.layout.item_garden
        GardenType.LAKE -> R.layout.item_lake
    }

    inner class GardenViewHolder(val binding: ItemGardenBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.ivGarden.setOnClickListener {
                if (selectedPosition != -1) {
                    removeEvent(currentList[selectedPosition].location)
                }

                selectedPosition = adapterPosition

                binding.garden?.let {
                    clickEvent(it.location)
                }
            }
        }

        fun bind(item: InventoryMind, selected: Boolean) {
            if (item.type == GardenType.EMPTY) {
                binding.ivGarden.isSelected = selected
            }
            binding.garden = item
            binding.executePendingBindings()
        }
    }

    class LakeViewHolder(val binding: ItemLakeBinding) : RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun from(parent: ViewGroup): LakeViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemLakeBinding.inflate(layoutInflater, parent, false)
                return LakeViewHolder(binding)
            }
        }
    }

    class GardenDiffUtil : DiffUtil.ItemCallback<InventoryMind>() {
        override fun areItemsTheSame(oldItem: InventoryMind, newItem: InventoryMind): Boolean {
            return oldItem.location == newItem.location
        }

        override fun areContentsTheSame(
            oldItem: InventoryMind,
            newItem: InventoryMind
        ): Boolean {
            return oldItem.tree == newItem.tree
        }
    }
}