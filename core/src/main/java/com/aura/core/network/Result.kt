package com.aura.core.network

sealed class Result<out S> {
    data class Success<S>(val data: S?) : Result<S>()
    data class Error<E>(val errors: CallException) : Result<E>()
}