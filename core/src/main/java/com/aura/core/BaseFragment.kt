package com.aura.core

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<Viewbinding : ViewBinding, Viewmodel : ViewModel>(@LayoutRes layout: Int) :
    Fragment(layout) {

    abstract val binding: Viewbinding

    abstract val viewModel: Viewmodel

    protected lateinit var navController: NavController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = findNavController()
        observes()
        initViewListeners()
        init(savedInstanceState)
    }

    protected fun navigateFragment(destination: Int, bundle: Bundle? = null) {
        navController.navigate(destination, bundle)
    }

    protected fun <T> LiveData<T>.observeNonNull(action: (T) -> Unit) {
        if (!this@BaseFragment.isAdded) return
        this.observe(viewLifecycleOwner, Observer { action(it ?: return@Observer) })
    }

    protected open fun initViewListeners() {}

    protected open fun observes() {}

    protected abstract fun init(savedInstanceState: Bundle?)
}