package com.mindgarden.mindgarden.ui.inventory.adapter.util

import android.content.ClipData
import android.util.Log
import android.view.DragEvent
import android.view.DragEvent.*
import android.view.View
import androidx.annotation.DrawableRes

class DragCallback(private val listener: OnDragListener): View.OnDragListener {

    interface OnDragListener {
        fun onDragEntered()
        fun onDragExited()
        fun onDrop(treeId: Int, @DrawableRes treeResId: Int)
    }

    override fun onDrag(v: View?, event: DragEvent?): Boolean {
        return when (event?.action) {
            ACTION_DRAG_STARTED -> {
                true
            }
            ACTION_DRAG_ENTERED -> {
                listener.onDragEntered()
                true
            }
            ACTION_DRAG_LOCATION -> {
                Log.d("DragCallback", "location item")
                true
            }
            ACTION_DRAG_EXITED -> {
                listener.onDragExited()
                true
            }
            ACTION_DROP -> {
                val item1: ClipData.Item = event.clipData.getItemAt(0)
                val item2 = event.clipData.getItemAt(1)
                val treeId = Integer.parseInt("${item1.text}")
                val drawableRes = Integer.parseInt("${item2.text}")
                Log.d("DragCallback", "started item id: $treeId drawable: $drawableRes")
                listener.onDrop(treeId, drawableRes)
                true
            }
            ACTION_DRAG_ENDED -> {
                true
            }
            else -> false
        }
    }

    companion object {
        const val LABEL_TREE_RES_ID = "treeResId"
        const val LABEL_TREE_ID = "treeId"
    }
}