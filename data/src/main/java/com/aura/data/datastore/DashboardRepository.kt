package com.aura.data.datastore

import com.aura.core.network.Result
import com.aura.data.model.sql.BonusEntity
import com.aura.data.model.sql.GradeEntity
import com.aura.data.model.sql.ProfitEntity
import com.aura.data.model.sql.RefillEntity
import kotlinx.coroutines.flow.Flow

interface DashboardRepository {

    suspend fun getBonus(): Result<BonusEntity>

    suspend fun getGrade(): Result<GradeEntity>

    suspend fun getProfit(): Result<ProfitEntity>

    suspend fun getRefill(): Result<RefillEntity>

}