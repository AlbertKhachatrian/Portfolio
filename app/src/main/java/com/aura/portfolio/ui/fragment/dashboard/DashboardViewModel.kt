package com.aura.portfolio.ui.fragment.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aura.core.network.Result
import com.aura.domain.interactor.GetDashboardDataInteractor
import com.aura.domain.model.DashboardModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DashboardViewModel(
    private val getDashboardDataInteractor: GetDashboardDataInteractor
) : ViewModel() {

    private val _data by lazy { MutableLiveData<List<DashboardModel>>() }
    val data: LiveData<List<DashboardModel>> get() = _data

    private val _loading by lazy { MutableLiveData<Boolean>() }
    val loading: LiveData<Boolean> get() = _loading

    fun getDashboardData() {
        _loading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            val data = mutableListOf<DashboardModel>()
            getDashboardDataInteractor().forEach {
                when (it) {
                    is Result.Success -> {
                        it.data?.let { it1 -> data.add(it1) }
                    }
                    is Result.Error -> {

                    }
                }
            }
            _data.postValue(data)
            _loading.postValue(false)
        }
    }
}