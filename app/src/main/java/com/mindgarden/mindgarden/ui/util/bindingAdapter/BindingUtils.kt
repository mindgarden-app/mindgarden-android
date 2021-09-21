package com.mindgarden.mindgarden.ui.util.bindingAdapter

import android.view.View
import androidx.databinding.BindingAdapter

@BindingAdapter("visible")
fun setVisible(view: View, isVisible: Boolean) {
    view.visibility = if (isVisible) View.VISIBLE else View.GONE
}