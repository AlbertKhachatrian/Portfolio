package com.aura.data.dataservice.sqlservice

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.aura.data.model.sql.GradeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface GradeDao {
    @Query("SELECT * FROM grade")
    fun getGrade(): Flow<GradeEntity>

    @Insert
    suspend fun saveGrade(bonus: GradeEntity)

    @Query("DELETE FROM grade")
    suspend fun deleteGrade()
}