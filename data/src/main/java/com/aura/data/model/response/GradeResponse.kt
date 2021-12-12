package com.aura.data.model.response

import com.google.gson.annotations.SerializedName

data class GradeResponse(
    @SerializedName("total")
    val total: Float?,
    @SerializedName("raise")
    val raise: Float?,
    @SerializedName("stock")
    val stock: Float?,
    @SerializedName("balanceUSD")
    val balanceUsd: Float?,
    @SerializedName("balanceRUB")
    val balanceRub: Float?,
    @SerializedName("about")
    val about: String?
)