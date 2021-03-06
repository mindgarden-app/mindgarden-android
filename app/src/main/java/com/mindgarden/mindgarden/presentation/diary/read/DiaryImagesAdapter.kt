package com.mindgarden.mindgarden.presentation.diary.read

import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mindgarden.mindgarden.databinding.ItemDiaryImageBinding
import com.mindgarden.mindgarden.presentation.diary.ImageDiffCallback

class DiaryImagesAdapter
    : ListAdapter<String, DiaryImagesAdapter.ViewHolder>(ImageDiffCallback()) {

    inner class ViewHolder(private val binding: ItemDiaryImageBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(uriString: String) {
            Log.d("ReadDiaryImagesAdapter", "bind: $uriString")
            Glide.with(binding.ivImageReadDiaryItem)
                .load(Uri.parse(uriString))
                .into(binding.ivImageReadDiaryItem)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemDiaryImageBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }
}