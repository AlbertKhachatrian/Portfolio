package com.aura.domain.mapper

import com.aura.data.model.sql.GradeEntity
import com.aura.domain.model.Grade

class GradeMapper: Mapper<GradeEntity?, Grade> {
    override fun map(it: GradeEntity?): Grade {
        return Grade(
            it?.total,
            it?.raise,
            it?.stock,
            it?.balanceUsd,
            it?.balanceRub,
            it?.about
        )
    }
}