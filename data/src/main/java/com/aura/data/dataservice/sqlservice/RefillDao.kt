package com.aura.data.dataservice.sqlservice

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.aura.data.model.sql.RefillEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RefillDao {
    @Query("SELECT * FROM refill")
    fun getRefill(): Flow<RefillEntity>

    @Insert
    suspend fun saveRefill(bonus: RefillEntity)

    @Query("DELETE FROM refill")
    suspend fun deleteRefill()
}