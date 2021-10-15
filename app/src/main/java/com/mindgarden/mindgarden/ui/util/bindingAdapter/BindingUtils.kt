package com.mindgarden.mindgarden.ui.util.bindingAdapter

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("visible")
fun setVisible(view: View, isVisible: Boolean) {
    view.visibility = if (isVisible) View.VISIBLE else View.GONE
}

@BindingAdapter("setDrawableRes")
fun setImageRes(view: ImageView, drawableRes: Int) {
    Glide.with(view.context)
        .load(drawableRes)
        .into(view)
}