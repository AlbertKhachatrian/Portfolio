package com.aura.data.model.response

import com.google.gson.annotations.SerializedName

data class BonusResponse(
    @SerializedName("totalRUB")
    val totalRub: Float?,
    @SerializedName("totalUSD")
    val totalUsd: Float?,
    @SerializedName("team")
    val team: Int?,
    @SerializedName("refillUSD")
    val refillUsd: Float?,
    @SerializedName("refillRUB")
    val refillRub: Float?,
    @SerializedName("about")
    val about: String?
)