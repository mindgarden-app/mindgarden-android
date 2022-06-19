package com.mindgarden.mindgarden.presentation.util

import android.content.res.Resources
import android.net.Uri
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.core.view.updateLayoutParams
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.mindgarden.mindgarden.R
import com.mindgarden.mindgarden.data.db.entity.Diary
import com.mindgarden.mindgarden.presentation.diarylist.DiaryListAdapter
import com.mindgarden.mindgarden.presentation.util.common.UIState
import com.mindgarden.mindgarden.util.ext.toStringOfPattern
import java.time.LocalDateTime

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
            setMargins(24f.dp, 0, 24f.dp, 0)
        }
    } else {
        updateLayoutParams<ViewGroup.MarginLayoutParams> {
            setMargins(24f.dp, 42f.dp, 24f.dp, 0)
        }
    }
}

@BindingAdapter("updateMargin")
fun TextView.updateMargin(ivIsVisible: Boolean) {
    if (ivIsVisible) {
        updateLayoutParams<ViewGroup.MarginLayoutParams> {
            setMargins(23f.dp, 37f.dp, 23f.dp, 0)
        }
    } else {
        updateLayoutParams<ViewGroup.MarginLayoutParams> {
            setMargins(23f.dp, 25f.dp, 23f.dp, 0)
        }
    }
}

val Float.dp: Int
    get() = (this * Resources.getSystem().displayMetrics.density + 0.5f).toInt()

@BindingAdapter("setReadDiaryTime")
fun TextView.setReadDiaryTime(localDateTime: LocalDateTime) {
    val time = localDateTime.toStringOfPattern(context.getString(R.string.pattern_read_diary))
    context.getString(R.string.read_diary_time, time)
    text = context.getString(R.string.read_diary_time, time)
}

// common
@BindingAdapter("setImageUri")
fun ImageView.setImageUri(uri: Uri?) {
    Glide.with(this.context)
        .load(uri)
        .transform(CenterCrop(), RoundedCorners(5f.dp))
        .into(this)
}

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

