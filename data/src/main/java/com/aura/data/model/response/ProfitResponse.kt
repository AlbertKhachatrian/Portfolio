package com.aura.data.model.response

import com.google.gson.annotations.SerializedName

data class ProfitResponse(
    @SerializedName("total")
    val total: Float?,
    @SerializedName("raise")
    val raise: Float?,
    @SerializedName("invest")
    val invest: Float?,
    @SerializedName("price")
    val price: Float?,
    @SerializedName("about")
    val about: String?
)