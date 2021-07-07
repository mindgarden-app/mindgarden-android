package com.mindgarden.mindgarden.ui.inventory.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mindgarden.mindgarden.R
import com.mindgarden.mindgarden.ui.inventory.adapter.listener.DragCallback
import com.mindgarden.mindgarden.ui.inventory.model.Item


class GardenAdapter(private val items: List<Item>):
    RecyclerView.Adapter<GardenAdapter.ViewHolder>(){

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_inventory, viewGroup,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tv.text = items[position].id
    }

    override fun getItemCount() = items.count()

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val tv: TextView = view.findViewById(R.id.tvNum)

        init {
            tv.setOnDragListener(DragCallback(object : DragCallback.OnDragListener {
                override fun onDrop(imgId: String) {
                    Log.d("GardenAdapter", "imgId: ${imgId}, to: ${tv.text}")
                }
            }))
        }
    }
}