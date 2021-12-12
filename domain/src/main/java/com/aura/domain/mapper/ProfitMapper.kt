package com.aura.domain.mapper

import com.aura.data.model.sql.ProfitEntity
import com.aura.domain.model.Profit

class ProfitMapper: Mapper<ProfitEntity?, Profit> {
    override fun map(it: ProfitEntity?): Profit {
        return Profit(
            it?.total,
            it?.raise,
            it?.invest,
            it?.price,
            it?.about
        )
    }
}