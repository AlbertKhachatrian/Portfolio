package com.aura.portfolio.ui.fragment.dashboard

import android.os.Bundle
import android.view.View
import com.aura.core.BaseFragment
import com.aura.core.viewBinding
import com.aura.portfolio.R
import com.aura.portfolio.databinding.FragmentDashboardBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class DashboardFragment: BaseFragment<FragmentDashboardBinding, DashboardViewModel>(R.layout.fragment_dashboard) {

    override val binding: FragmentDashboardBinding by viewBinding(FragmentDashboardBinding::bind)

    override val viewModel: DashboardViewModel by viewModel()

    private val adapter = DashboardAdapter()

    override fun init(savedInstanceState: Bundle?) {
        binding.recycler.adapter = adapter
        viewModel.getDashboardData()
    }

    override fun observes() {
        viewModel.data.observeNonNull {
            adapter.submitList(it)
        }
        viewModel.loading.observeNonNull {
            if(it) binding.progress.visibility = View.VISIBLE else binding.progress.visibility = View.GONE
        }
    }

}