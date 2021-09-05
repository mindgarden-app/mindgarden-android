package com.mindgarden.mindgarden.ui.util.base

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.os.PersistableBundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel

abstract class BaseActivity<VM: ViewModel, VDB: ViewDataBinding>(@LayoutRes private val layoutId: Int) : AppCompatActivity() {
    protected abstract val viewModel: VM
    protected abstract fun setViewModel()
    protected lateinit var binding: VDB

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        binding = DataBindingUtil.setContentView(this, layoutId)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
    }

}