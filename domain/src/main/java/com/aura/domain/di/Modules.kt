package com.aura.domain.di

import com.aura.domain.interactor.GetDashboardDataInteractor
import com.aura.domain.mapper.BonusMapper
import com.aura.domain.mapper.GradeMapper
import com.aura.domain.mapper.ProfitMapper
import com.aura.domain.mapper.RefillMapper
import com.aura.domain.usecase.GetDashboardDataUseCase
import org.koin.dsl.module

val domainModule = module {

    factory { BonusMapper() }
    factory { GradeMapper() }
    factory { RefillMapper() }
    factory { ProfitMapper() }

    factory<GetDashboardDataInteractor> { GetDashboardDataUseCase(get(), get(), get(), get(), get()) }

}