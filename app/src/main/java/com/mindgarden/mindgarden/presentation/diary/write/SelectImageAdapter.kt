package com.mindgarden.mindgarden.presentation.diary.write

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mindgarden.mindgarden.databinding.ItemSelectImageBinding
import com.mindgarden.mindgarden.presentation.diary.ImageDiffCallback

class SelectImageAdapter(
    private val onDeleteButtonClickListener: (Int) -> Unit,
    private val listChangeListener: (List<String>) -> Unit
    ) :
    ListAdapter<String, SelectImageAdapter.ViewHolder>(ImageDiffCallback()) {

    inner class ViewHolder(private val binding: ItemSelectImageBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.btnDeleteImage.setOnClickListener {
                onDeleteButtonClickListener(adapterPosition)
            }
        }

        fun bind(uriString: String) {
            Glide.with(binding.ivSelectImage)
                .load(Uri.parse(uriString))
                .centerCrop()
                .into(binding.ivSelectImage)
        }
    }

    fun removeItem(position: Int) {
        val newList = currentList.toMutableList()
        newList.removeAt(position)
        submitList(newList)
    }

    override fun onCurrentListChanged(
        previousList: MutableList<String>,
        currentList: MutableList<String>
    ) {
        super.onCurrentListChanged(previousList, currentList)
        listChangeListener(currentList)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemSelectImageBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

}