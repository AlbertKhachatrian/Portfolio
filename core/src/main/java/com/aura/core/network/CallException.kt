package com.aura.core.network

data class CallException(
    val errorCode: Int,
    val errorMessage: String? = null,
    val errorBody: String? = null
)