package com.aura.domain.interactor

import com.aura.core.network.Result
import com.aura.domain.model.DashboardModel

interface GetDashboardDataInteractor {

    suspend operator fun invoke(): List<Result<DashboardModel>>

}