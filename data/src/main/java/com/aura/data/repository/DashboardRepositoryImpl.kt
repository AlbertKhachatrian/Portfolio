package com.aura.data.repository

import com.aura.core.network.Result
import com.aura.core.network.analyzeResponse
import com.aura.core.network.makeApiCall
import com.aura.core.network.networkBoundService
import com.aura.data.dataservice.apiservice.DashboardApi
import com.aura.data.dataservice.sqlservice.BonusDao
import com.aura.data.dataservice.sqlservice.GradeDao
import com.aura.data.dataservice.sqlservice.ProfitDao
import com.aura.data.dataservice.sqlservice.RefillDao
import com.aura.data.datastore.DashboardRepository
import com.aura.data.dataservice.appservice.DataSource
import com.aura.data.model.sql.BonusEntity
import com.aura.data.model.sql.GradeEntity
import com.aura.data.model.sql.ProfitEntity
import com.aura.data.model.sql.RefillEntity
import kotlinx.coroutines.flow.Flow

class DashboardRepositoryImpl(
    private val dashboardApi: DashboardApi,
    private val bonusDao: BonusDao,
    private val gradeDao: GradeDao,
    private val profitDao: ProfitDao,
    private val refillDao: RefillDao,
    private val dataSource: DataSource
) : DashboardRepository {
    override suspend fun getBonus(): Flow<Result<BonusEntity>> {
        return networkBoundService(
            query = bonusDao::getBonus,
            fetch = {
                makeApiCall({
                    analyzeResponse(dashboardApi.getBonus())
                })
            },
            saveFetchResult = {
                bonusDao.deleteBonus()
                bonusDao.saveBonus(it)
            },
            responseMapper = {
                BonusEntity(
                    totalRub = it.totalRub,
                    totalUsd = it.totalUsd,
                    team = it.team,
                    refillUsd = it.refillUsd,
                    refillRub = it.refillRub,
                    about = it.about
                )
            },
            dataSource.isInternetAvailable()
        )
    }

    override suspend fun getGrade(): Flow<Result<GradeEntity>> {
        return networkBoundService(
            query = gradeDao::getGrade,
            fetch = {
                makeApiCall({
                    analyzeResponse(dashboardApi.getGrade())
                })
            },
            saveFetchResult = {
                gradeDao.deleteGrade()
                gradeDao.saveGrade(it)
            },
            responseMapper = {
                GradeEntity(
                    total = it.total,
                    raise = it.raise,
                    stock = it.stock,
                    balanceUsd = it.balanceUsd,
                    balanceRub = it.balanceRub,
                    about = it.about
                )
            },
            dataSource.isInternetAvailable()
        )
    }

    override suspend fun getProfit(): Flow<Result<ProfitEntity>> {
        return networkBoundService(
            query = profitDao::getProfit,
            fetch = {
                makeApiCall({
                    analyzeResponse(dashboardApi.getProfit())
                })
            },
            saveFetchResult = {
                profitDao.deleteProfit()
                profitDao.saveProfit(it)
            },
            responseMapper = {
                ProfitEntity(
                    total = it.total,
                    raise = it.raise,
                    invest = it.invest,
                    price = it.price,
                    about = it.about
                )
            },
            dataSource.isInternetAvailable()
        )
    }

override suspend fun getRefill(): Flow<Result<RefillEntity>> {
    return networkBoundService(
        query = refillDao::getRefill,
        fetch = {
            makeApiCall({
                analyzeResponse(dashboardApi.getRefill())
            })
        },
        saveFetchResult = {
            refillDao.deleteRefill()
            refillDao.saveRefill(it)
        },
        responseMapper = {
            RefillEntity(
                total = it.total,
                withdrawn = it.withdrawn,
                refillUsd = it.refillUsd,
                refillRub = it.refillRub,
                about = it.about
            )
        },
        dataSource.isInternetAvailable()
    )
}
}