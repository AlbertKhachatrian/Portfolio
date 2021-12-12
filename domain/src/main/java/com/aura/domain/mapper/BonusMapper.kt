package com.aura.domain.mapper

import com.aura.data.model.sql.BonusEntity
import com.aura.domain.model.Bonus

class BonusMapper: Mapper<BonusEntity?, Bonus> {
    override fun map(it: BonusEntity?): Bonus {
        return Bonus(
            it?.totalRub,
            it?.totalUsd,
            it?.team,
            it?.refillUsd,
            it?.refillRub,
            it?.about
        )
    }
}