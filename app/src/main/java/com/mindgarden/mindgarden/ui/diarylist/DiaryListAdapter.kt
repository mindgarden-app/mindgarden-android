package com.mindgarden.mindgarden.ui.diarylist

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mindgarden.mindgarden.R
import com.mindgarden.mindgarden.data.model.entity.Diary
import com.mindgarden.mindgarden.databinding.RvItemDiaryListBinding

class DiaryListAdapter(val diaryListItemClick: (Diary) -> Unit, val diaryListItemLongClick: (Diary) -> Unit) :
        ListAdapter<Diary, DiaryListAdapter.DiaryListViewHolder>(
            DiaryListDiffCallback
        ) {
            inner class DiaryListViewHolder(private val binding : RvItemDiaryListBinding) :
                    RecyclerView.ViewHolder(binding.root) {
                private val tvDel = itemView.findViewById<TextView>(R.id.tv_delete)

                        fun bind(diary: Diary) {
                            binding.diary = diary

                            binding.llDiaryListContainer.setOnClickListener {
                                diaryListItemClick(diary)
                            }

                            tvDel.setOnLongClickListener {
                                diaryListItemLongClick(diary)
                                true
                            }
                        }
                    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiaryListViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding : RvItemDiaryListBinding = DataBindingUtil.inflate(layoutInflater, R.layout.rv_item_diary_list, parent, false)
        return DiaryListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DiaryListViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}



