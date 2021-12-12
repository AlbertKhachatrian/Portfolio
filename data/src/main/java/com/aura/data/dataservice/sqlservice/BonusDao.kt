package com.aura.data.dataservice.sqlservice

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.aura.data.model.sql.BonusEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BonusDao {

    @Query("SELECT * FROM bonus")
    fun getBonus(): Flow<BonusEntity>

    @Insert
    suspend fun saveBonus(bonus: BonusEntity)

    @Query("DELETE FROM bonus")
    suspend fun deleteBonus()
}