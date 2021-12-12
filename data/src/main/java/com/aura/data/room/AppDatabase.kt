package com.aura.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.aura.data.dataservice.sqlservice.BonusDao
import com.aura.data.dataservice.sqlservice.GradeDao
import com.aura.data.dataservice.sqlservice.ProfitDao
import com.aura.data.dataservice.sqlservice.RefillDao
import com.aura.data.model.sql.BonusEntity
import com.aura.data.model.sql.GradeEntity
import com.aura.data.model.sql.ProfitEntity
import com.aura.data.model.sql.RefillEntity

@Database(
    entities = [BonusEntity::class, GradeEntity::class, ProfitEntity::class, RefillEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase: RoomDatabase() {
    abstract fun bonusDao():BonusDao

    abstract fun gradeDao():GradeDao

    abstract fun profitDao():ProfitDao

    abstract fun refillDao():RefillDao
}