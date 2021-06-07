package com.mindgarden.mindgarden.ui.inventory.adapter

import android.content.ClipData
import android.content.ClipDescription
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Point
import android.graphics.drawable.ColorDrawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mindgarden.mindgarden.R
import com.mindgarden.mindgarden.ui.inventory.model.Item

class InventoryAdapter(private val items: MutableList<Item>): RecyclerView.Adapter<InventoryAdapter.ViewHolder>(){

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_inventory, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tv.text = items[position].id
    }

    override fun getItemCount() = items.count()

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tv: TextView = view.findViewById(R.id.tvNum)

        init {
            tv.tag = tv.text
            tv.setOnLongClickListener { v ->
                val dragData = ClipData.newPlainText("id", tv.text.toString())
                val shadow = MyDragShadowBuilder(v)
                v.startDragAndDrop(dragData, shadow, null, 0)
            }
        }
    }

    private class MyDragShadowBuilder(v: View) : View.DragShadowBuilder(v) {
        private val shadow = ColorDrawable(Color.LTGRAY)

        override fun onProvideShadowMetrics(size: Point, touch: Point) {
            val width: Int = (view.width / 1.2F).toInt()
            val height: Int = (view.height / 1.2F).toInt()

            shadow.setBounds(0, 0, width, height)
            size.set(width, height)
            touch.set(width / 2, height / 2)
        }
        override fun onDrawShadow(canvas: Canvas) {
            shadow.draw(canvas)
        }
    }
}