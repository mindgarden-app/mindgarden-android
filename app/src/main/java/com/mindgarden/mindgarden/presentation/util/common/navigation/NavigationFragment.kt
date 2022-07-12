package com.mindgarden.mindgarden.presentation.util.common.navigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.findNavController
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Navigation Event 를 받을 수 있는 Base Fragment 입니다
 */
abstract class NavigationFragment<VM : NavigationViewModel, VDB : ViewDataBinding>(@LayoutRes private val layoutId: Int) :
    Fragment() {
    protected abstract val viewModel: VM
    protected abstract fun setViewModel()
    protected lateinit var binding: VDB
    lateinit var fragmentScope: LifecycleCoroutineScope
    lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        fragmentScope = viewLifecycleOwner.lifecycleScope
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = view.findNavController()
        setViewModel()
        observeData()

        fragmentScope.launch(Dispatchers.Main) {
            observeNavigation()
        }
    }

    open fun observeData() = Unit

    /**
     * Navigation event
     */
    private suspend fun observeNavigation() {
        viewModel.navigation.events.collect {
            handleNavigation(it)
        }
    }

    private fun handleNavigation(navCommand: NavigationCommand) {
        when (navCommand) {
            is NavigationCommand.ToDirection -> {
                navController.navigate(navCommand.directions)
            }
            is NavigationCommand.PopBackStackWithResult -> {
                navController.previousBackStackEntry?.savedStateHandle?.set(
                    navCommand.key,
                    navCommand.result
                )
                navController.popBackStack()
            }
            is NavigationCommand.Back -> {
                val result = navController.navigateUp()
                if (!result) {
                    activity?.finish()
                }
            }
        }
    }
}