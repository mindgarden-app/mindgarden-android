package com.mindgarden.mindgarden.ui.diarylist

import androidx.recyclerview.widget.DiffUtil
import com.mindgarden.mindgarden.data.model.Diary

object DiaryListDiffCallback : DiffUtil.ItemCallback<Diary>() {
    override fun areItemsTheSame(oldItem: Diary, newItem: Diary): Boolean {
        return (oldItem.idx == newItem.idx && oldItem.content == newItem.content)
    }

    override fun areContentsTheSame(oldItem: Diary, newItem: Diary): Boolean {
        return oldItem == newItem
    }
}