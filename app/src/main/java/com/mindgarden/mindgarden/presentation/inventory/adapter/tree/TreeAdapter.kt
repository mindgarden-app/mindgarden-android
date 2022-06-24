package com.mindgarden.mindgarden.presentation.inventory.adapter.tree

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.mindgarden.mindgarden.R
import com.mindgarden.mindgarden.databinding.ItemTreeBinding
import com.mindgarden.mindgarden.presentation.inventory.model.Tree
import kotlin.properties.Delegates

class TreeAdapter(val onSelectedListener: (tree: Tree) -> Unit) :
    RecyclerView.Adapter<TreeAdapter.ViewHolder>() {

    private val trees = Tree.values()

    private var selectedPosition by Delegates.observable(0) { _, oldPos, newPos ->
        if (newPos in trees.indices) {
            notifyItemChanged(oldPos)
            notifyItemChanged(newPos)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil.inflate<ItemTreeBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_tree,
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (position in trees.indices) {
            holder.bind(trees[position], position == selectedPosition)
        }
    }

    override fun getItemCount() = trees.size

    inner class ViewHolder(val binding: ItemTreeBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.ivTree.setOnClickListener {
                selectedPosition = adapterPosition
                binding.tree?.let {
                    onSelectedListener(it)
                }
            }
        }

        fun bind(item: Tree?, selected: Boolean) {
            binding.tree = item
            binding.ivTree.isSelected = selected
        }
    }
}