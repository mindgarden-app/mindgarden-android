package com.mindgarden.mindgarden.util.ext

import android.content.Context
import android.content.res.Resources
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.ShapeDrawable
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import com.mindgarden.mindgarden.R

// array resource
/**
 * Typed Array should be recycled after use with "recycle()"
 */
fun Resources.getTreeResArray() = this.obtainTypedArray(R.array.tree_array)

fun Resources.getLocationArray() = this.getIntArray(R.array.garden_location_array)

// drawable resource
fun Drawable.overrideColor(@ColorInt colorInt: Int) {
    when (this) {
        is GradientDrawable -> setColor(colorInt)
        is ShapeDrawable -> paint.color = colorInt
        is ColorDrawable -> color = colorInt
    }
}

fun Context.getColorResId(@ColorRes colorRes: Int) = ContextCompat.getColor(this, colorRes)
