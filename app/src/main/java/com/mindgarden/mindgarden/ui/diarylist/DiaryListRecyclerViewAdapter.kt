package com.mindgarden.mindgarden.ui.diarylist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mindgarden.mindgarden.R
import com.mindgarden.mindgarden.data.model.Diary

class DiaryListRecyclerViewAdapter(val diaryListItemClick: (Diary) -> Unit, val diaryListItemLongClick: (Diary) -> Unit)
    : RecyclerView.Adapter<DiaryListRecyclerViewAdapter.ViewHolder>() {
    private var diaries: List<Diary> = listOf()
    private var isPressed : Boolean = false

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
        private val tvContent = itemView.findViewById<TextView>(R.id.tv_diary_list_content)
        private val llDel = itemView.findViewById<LinearLayout>(R.id.ll_diary_list_delete)
        private val imgDel = itemView.findViewById<ImageView>(R.id.img_diary_list_delete)

        fun bind(diary: Diary) {
            tvContent.text = diary.content

            itemView.setOnClickListener {
                diaryListItemClick(diary)
            }

            itemView.setOnLongClickListener {
                // diaryListItemLongClick(diary)
                isPressed = !isPressed

                if (isPressed) {
                    llDel.visibility = View.VISIBLE

                    imgDel.setOnLongClickListener {
                        diaryListItemLongClick(diary)
                        true
                    }
                } else llDel.visibility = View.GONE
                true
            }
        }
    }

    fun setDiaries(diaries: List<Diary>) {
        this.diaries = diaries
        notifyDataSetChanged()
    }
}
