package com.aura.domain.mapper

import com.aura.data.model.sql.RefillEntity
import com.aura.domain.model.Refill

class RefillMapper: Mapper<RefillEntity?, Refill> {
    override fun map(it: RefillEntity?): Refill {
        return Refill(
            it?.total,
            it?.withdrawn,
            it?.refillUsd,
            it?.refillRub,
            it?.about
        )
    }
}