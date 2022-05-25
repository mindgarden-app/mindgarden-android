package com.mindgarden.mindgarden.presentation.util

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mindgarden.mindgarden.data.db.entity.Diary
import com.mindgarden.mindgarden.presentation.diarylist.DiaryListAdapter
import com.mindgarden.mindgarden.presentation.util.common.UIState

// DiaryList
@BindingAdapter("setDiaryList")
fun RecyclerView.setDiaryList(state: UIState<List<Diary>>) {
    val adapter = this.adapter
    if (state is UIState.Success) {
        (adapter as DiaryListAdapter).submitList(state.data)
    }
}

// Diary
@BindingAdapter("setVisibility")
fun ProgressBar.setVisibility(state: UIState<Long>) {
    this.visibility = if (state is UIState.Loading) View.VISIBLE else View.GONE
}

@BindingAdapter("setResult")
fun Button.setResult(state: UIState<Long>) {
    this.text = when (state) {
        is UIState.Error -> "Error"
        UIState.Loading -> "Write Diary"
        is UIState.Success -> "success write diary. id: ${state.data}"
    }
}

// common
@BindingAdapter("setDrawableRes")
fun setImageRes(view: ImageView, drawableRes: Int) {
    Glide.with(view.context)
        .load(drawableRes)
        .into(view)
}