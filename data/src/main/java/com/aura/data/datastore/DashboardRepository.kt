package com.aura.data.datastore

import com.aura.core.network.Result
import com.aura.data.model.sql.BonusEntity
import com.aura.data.model.sql.GradeEntity
import com.aura.data.model.sql.ProfitEntity
import com.aura.data.model.sql.RefillEntity
import kotlinx.coroutines.flow.Flow

interface DashboardRepository {

    suspend fun getBonus(): Flow<Result<BonusEntity>>

    suspend fun getGrade(): Flow<Result<GradeEntity>>

    suspend fun getProfit(): Flow<Result<ProfitEntity>>

    suspend fun getRefill(): Flow<Result<RefillEntity>>

}