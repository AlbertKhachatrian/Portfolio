package com.aura.data.dataservice.apiservice

import com.aura.data.model.response.BonusResponse
import com.aura.data.model.response.GradeResponse
import com.aura.data.model.response.ProfitResponse
import com.aura.data.model.response.RefillResponse
import retrofit2.Response
import retrofit2.http.GET

interface DashboardApi {
    @GET("dashboards/bonus")
    suspend fun getBonus(): Response<BonusResponse>

    @GET("dashboards/grade")
    suspend fun getGrade(): Response<GradeResponse>

    @GET("dashboards/refill")
    suspend fun getRefill(): Response<RefillResponse>

    @GET("dashboards/profit")
    suspend fun getProfit(): Response<ProfitResponse>
}