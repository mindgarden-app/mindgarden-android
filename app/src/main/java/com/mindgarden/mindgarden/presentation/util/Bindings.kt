package com.mindgarden.mindgarden.presentation.util

import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.core.view.marginTop
import androidx.core.view.updateLayoutParams
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mindgarden.mindgarden.R
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

@BindingAdapter("updateMargin")
fun EditText.updateMargin(rvIsVisible: Boolean) {
    if (rvIsVisible) {
        updateLayoutParams<ViewGroup.MarginLayoutParams> {
            setMargins(24, 0, 24,0)
        }
    } else {
        updateLayoutParams<ViewGroup.MarginLayoutParams> {
            setMargins(24, 42, 24,0)
        }
    }
}


// common
@BindingAdapter("setDrawableRes")
fun ImageView.setImageRes(drawableRes: Int) {
    Glide.with(this.context)
        .load(drawableRes)
        .into(this)
}

@BindingAdapter("setImageButtonRes")
fun ImageButton.setImageButtonRes(@DrawableRes res: Int) {
    Glide.with(this.context).load(res).into(this)
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

