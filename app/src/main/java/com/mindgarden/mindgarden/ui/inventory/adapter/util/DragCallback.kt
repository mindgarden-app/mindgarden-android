package com.mindgarden.mindgarden.ui.inventory.adapter.util

import android.content.ClipData
import android.util.Log
import android.view.DragEvent
import android.view.DragEvent.*
import android.view.View

class DragCallback(private val listener: OnDragListener): View.OnDragListener {

    interface OnDragListener {
        fun onDragEntered()
        fun onDragExited()
        fun onDrop(imgId: String)
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
                val item: ClipData.Item = event.clipData.getItemAt(0)
                val itemId = item.text
                Log.d("DragCallback", "started item id: $itemId")
                listener.onDrop(itemId.toString())
                true
            }
            ACTION_DRAG_ENDED -> {
                true
            }
            else -> false
        }
    }
}