package com.aura.data.model.sql

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "profit")
data class ProfitEntity(
    @PrimaryKey
    val id: Int? = null,
    @ColumnInfo(name = "total")
    val total: Float?,
    @ColumnInfo(name = "raise")
    val raise: Float?,
    @ColumnInfo(name = "invest")
    val invest: Float?,
    @ColumnInfo(name = "price")
    val price: Float?,
    @ColumnInfo(name = "about")
    val about: String?
)