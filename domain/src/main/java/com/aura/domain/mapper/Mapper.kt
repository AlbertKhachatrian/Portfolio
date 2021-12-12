package com.aura.domain.mapper

interface Mapper<T, R> {
    fun map(it: T): R
}