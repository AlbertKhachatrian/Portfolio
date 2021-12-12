package com.aura.domain.model

data class Refill(
    val total: Float?,
    val withdrawn: Float?,
    val refillUsd: Float?,
    val refillRub: Float?,
    val about: String?
):DashboardModel