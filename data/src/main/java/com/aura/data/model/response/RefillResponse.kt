package com.aura.data.model.response

import com.google.gson.annotations.SerializedName

data class RefillResponse(
    @SerializedName("total")
    val total: Float?,
    @SerializedName("withdrawn")
    val withdrawn: Float?,
    @SerializedName("refillUSD")
    val refillUsd: Float?,
    @SerializedName("refillRUB")
    val refillRub: Float?,
    @SerializedName("about")
    val about: String?
)