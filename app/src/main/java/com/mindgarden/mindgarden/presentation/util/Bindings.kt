package com.mindgarden.mindgarden.presentation.util

import android.view.View
import android.widget.*
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.LayoutRes
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mindgarden.mindgarden.R
import com.mindgarden.mindgarden.data.db.entity.Diary
import com.mindgarden.mindgarden.presentation.diarylist.DiaryListAdapter
import com.mindgarden.mindgarden.presentation.util.common.GardenToolbar
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

@BindingAdapter("setImageButtonRes")
fun ImageButton.setImageButtonRes(@DrawableRes res: Int) {
    this.setImageResource(res)
}

@BindingAdapter("setTextViewBackground")
fun TextView.setTextViewBackground(@ColorRes bgColor: Int) {
    when (bgColor) {
        R.color.garden_green -> {
            setBackgroundResource(R.drawable.toolbar_green_button)
            setTextColor(ContextCompat.getColor(this.context, R.color.white))
        }
        R.color.gray_600 -> {
            setBackgroundResource(R.drawable.toolbar_gray_button)
            setTextColor(ContextCompat.getColor(this.context, R.color.gray_600))
        }
    }
}

