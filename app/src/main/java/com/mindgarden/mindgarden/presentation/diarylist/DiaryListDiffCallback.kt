package com.mindgarden.mindgarden.presentation.diarylist

import androidx.recyclerview.widget.DiffUtil
import com.mindgarden.mindgarden.data.db.entity.Diary

object DiaryListDiffCallback : DiffUtil.ItemCallback<Diary>() {
    override fun areItemsTheSame(oldItem: Diary, newItem: Diary): Boolean {
        return oldItem.idx == newItem.idx
    }

    override fun areContentsTheSame(oldItem: Diary, newItem: Diary): Boolean {
        return oldItem == newItem
    }
}