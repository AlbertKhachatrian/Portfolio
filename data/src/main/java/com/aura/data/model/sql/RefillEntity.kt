package com.aura.data.model.sql

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "refill")
data class RefillEntity(
    @PrimaryKey
    val id: Int? = null,
    @ColumnInfo(name = "total")
    val total: Float?,
    @ColumnInfo(name = "withdrawn")
    val withdrawn: Float?,
    @ColumnInfo(name = "refillUsd")
    val refillUsd: Float?,
    @ColumnInfo(name = "refillRub")
    val refillRub: Float?,
    @ColumnInfo(name = "about")
    val about: String?
)