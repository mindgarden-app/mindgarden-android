package com.mindgarden.mindgarden.ui.diarylist

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mindgarden.mindgarden.R
import com.mindgarden.mindgarden.data.model.Diary
import com.mindgarden.mindgarden.databinding.RvItemDiaryListBinding

class DiaryListAdapter(val diaryListItemClick: (Diary) -> Unit, val diaryListItemLongClick: (Diary) -> Unit) :
        ListAdapter<Diary, DiaryListAdapter.DiaryListViewHolder>(
            DiaryListDiffCallback
        ) {
    private var isPressed : Boolean = false

            inner class DiaryListViewHolder(private val binding : RvItemDiaryListBinding) :
                    RecyclerView.ViewHolder(binding.root) {
                //private val llDel = itemView.findViewById<LinearLayout>(R.id.ll_diary_list_delete)
                //private val imgDel = itemView.findViewById<ImageView>(R.id.img_diary_list_delete)
                private val del = itemView.findViewById<TextView>(R.id.tv_delete)

                        fun bind(diary: Diary) {
                            binding.diary = diary

                            itemView.setOnClickListener {
                                diaryListItemClick(diary)
                            }

                            del.setOnLongClickListener {
                                diaryListItemLongClick(diary)
                                true
                            }

                            /*itemView.setOnLongClickListener {
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
                            }*/
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



