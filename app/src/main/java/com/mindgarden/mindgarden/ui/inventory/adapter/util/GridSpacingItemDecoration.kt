package com.mindgarden.mindgarden.ui.inventory.adapter.util

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.annotation.DimenRes
import androidx.recyclerview.widget.RecyclerView

class GridSpacingItemDecoration() : RecyclerView.ItemDecoration() {
    private var _itemOffset = ItemOffset(0, 0, 0, 0)

    constructor(context: Context, itemOffset: ItemOffset) : this() {
        _itemOffset.top = itemOffset.top?.let { context.resources.getDimensionPixelSize(it) }
        _itemOffset.left = itemOffset.left?.let { context.resources.getDimensionPixelSize(it) }
        _itemOffset.bottom = itemOffset.bottom?.let { context.resources.getDimensionPixelSize(it) }
        _itemOffset.right = itemOffset.right?.let { context.resources.getDimensionPixelSize(it) }
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        _itemOffset.top?.let { outRect.top = it }
        _itemOffset.left?.let { outRect.left = it }
        _itemOffset.bottom?.let { outRect.bottom = it }
        _itemOffset.right?.let { outRect.right = it }
    }


    data class ItemOffset(
        @DimenRes var left: Int?,
        @DimenRes var top: Int?,
        @DimenRes var right: Int?,
        @DimenRes var bottom: Int?)
}