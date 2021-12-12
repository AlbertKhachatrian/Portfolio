package com.aura.data.dataservice.sqlservice

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.aura.data.model.sql.ProfitEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ProfitDao {
    @Query("SELECT * FROM profit")
    fun getProfit(): Flow<ProfitEntity>

    @Insert
    suspend fun saveProfit(bonus: ProfitEntity)

    @Query("DELETE FROM profit")
    suspend fun deleteProfit()
}