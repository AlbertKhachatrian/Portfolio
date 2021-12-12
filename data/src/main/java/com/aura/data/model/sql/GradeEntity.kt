package com.aura.data.model.sql

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "grade")
data class GradeEntity(
    @PrimaryKey
    val id: Int? = null,
    @ColumnInfo(name = "total")
    val total: Float?,
    @ColumnInfo(name = "raise")
    val raise: Float?,
    @ColumnInfo(name = "stock")
    val stock: Float?,
    @ColumnInfo(name = "balanceUsd")
    val balanceUsd: Float?,
    @ColumnInfo(name = "balanceRub")
    val balanceRub: Float?,
    @ColumnInfo(name = "about")
    val about: String?
)