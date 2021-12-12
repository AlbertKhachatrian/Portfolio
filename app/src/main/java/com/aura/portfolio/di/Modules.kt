package com.aura.portfolio.di

import com.aura.portfolio.ui.fragment.dashboard.DashboardViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { DashboardViewModel(get()) }
}