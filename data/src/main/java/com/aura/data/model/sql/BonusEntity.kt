package com.aura.data.model.sql

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bonus")
data class BonusEntity(
    @PrimaryKey
    val id: Int? = null,
    @ColumnInfo(name = "totalRub")
    val totalRub: Float?,
    @ColumnInfo(name = "totalUsd")
    val totalUsd: Float?,
    @ColumnInfo(name = "team")
    val team: Int?,
    @ColumnInfo(name = "refillUsd")
    val refillUsd: Float?,
    @ColumnInfo(name = "refillRub")
    val refillRub: Float?,
    @ColumnInfo(name = "about")
    val about: String?
)