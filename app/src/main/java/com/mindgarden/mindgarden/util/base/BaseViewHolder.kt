package com.mindgarden.mindgarden.util.base

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<out B: ViewDataBinding, in T>(
    parent: ViewGroup, @LayoutRes layout: Int
) : RecyclerView.ViewHolder(LayoutInflater.from(parent.context).inflate(layout, parent, false)) {

    protected val context: Context = itemView.context
    protected val binding: B = DataBindingUtil.bind(itemView)!!

    abstract fun bind(item: T)
}