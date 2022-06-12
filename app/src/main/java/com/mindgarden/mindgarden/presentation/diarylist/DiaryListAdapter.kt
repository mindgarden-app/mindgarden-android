package com.mindgarden.mindgarden.presentation.diarylist

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mindgarden.mindgarden.R
import com.mindgarden.mindgarden.data.db.entity.Diary
import com.mindgarden.mindgarden.databinding.RvItemDiaryListBinding
import java.time.format.DateTimeFormatter
import java.util.*

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
        holder.itemView.findViewById<TextView>(R.id.tv_diary_list_day_num).text = getItem(position).date.dayOfMonth.toString()
        holder.itemView.findViewById<TextView>(R.id.tv_diary_list_day_text).text = getItem(position).date.format(DateTimeFormatter.ofPattern("E").withLocale(
            Locale.forLanguageTag("en")
        ))
        Log.e("인덱스 : ", getItem(position).idx.toString())
    }
}



