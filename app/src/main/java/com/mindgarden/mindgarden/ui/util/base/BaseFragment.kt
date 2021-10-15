package com.mindgarden.mindgarden.ui.util.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable

abstract class BaseFragment<VM: ViewModel, VDB: ViewDataBinding>(@LayoutRes private val layoutId: Int) : Fragment() {
    protected abstract val viewModel: VM
    protected abstract fun setViewModel()
    protected lateinit var binding: VDB
    protected val compositeDisposable = CompositeDisposable()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setViewModel()
        observeData()
    }

    open fun observeData() = Unit

    override fun onDestroy() {
        compositeDisposable.clear()
        super.onDestroy()
    }
}