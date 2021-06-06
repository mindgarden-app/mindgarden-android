package com.mindgarden.mindgarden.adapter

import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mindgarden.mindgarden.R
import com.mindgarden.mindgarden.model.Diary

class DiaryListRecyclerViewAdapter(val diaryListItemLongClick: (Diary) -> Unit)
    : RecyclerView.Adapter<DiaryListRecyclerViewAdapter.ViewHolder>() {
    private var diaries: List<Diary> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, i: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rv_item_diary_list, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return diaries.size
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(diaries[position])
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val contentTv = itemView.findViewById<TextView>(R.id.tv_item_diary_content)

        fun bind(diary: Diary) {
            contentTv.text = diary.content

            itemView.setOnLongClickListener {
                diaryListItemLongClick(diary)
                true
            }
        }
    }

    fun setContacts(diaries: List<Diary>) {
        this.diaries = diaries
        notifyDataSetChanged()
    }
}
