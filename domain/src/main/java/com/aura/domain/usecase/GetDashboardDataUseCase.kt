package com.aura.domain.usecase

import com.aura.core.network.Result
import com.aura.data.datastore.DashboardRepository
import com.aura.domain.interactor.GetDashboardDataInteractor
import com.aura.domain.mapper.BonusMapper
import com.aura.domain.mapper.GradeMapper
import com.aura.domain.mapper.ProfitMapper
import com.aura.domain.mapper.RefillMapper
import com.aura.domain.model.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext

class GetDashboardDataUseCase(
    private val dashboardRepository: DashboardRepository,
    private val profitMapper: ProfitMapper,
    private val bonusMapper: BonusMapper,
    private val refillMapper: RefillMapper,
    private val gradeMapper: GradeMapper
) : GetDashboardDataInteractor {

    override suspend fun invoke(): List<Result<DashboardModel>> {
        return withContext(Dispatchers.IO){
            val profit = async { dashboardRepository.getProfit() }.await().first()
            val grade = async { dashboardRepository.getGrade() }.await().first()
            val refill = async { dashboardRepository.getRefill() }.await().first()
            val bonus = async { dashboardRepository.getBonus() }.await().first()
            val mProfit: Result<Profit> = when(profit){
                is Result.Success -> {
                    Result.Success(profitMapper.map(profit.data))
                }
                is Result.Error -> {
                    Result.Error(profit.errors)
                }
            }
            val mGrade: Result<Grade> = when(grade){
                is Result.Success -> {
                    Result.Success(gradeMapper.map(grade.data))
                }
                is Result.Error -> {
                    Result.Error(grade.errors)
                }
            }
            val mRefill: Result<Refill> = when(refill){
                is Result.Success -> {
                    Result.Success(refillMapper.map(refill.data))
                }
                is Result.Error ->{
                    Result.Error(refill.errors)
                }
            }
            val mBonus: Result<Bonus> = when(bonus){
                is Result.Success -> {
                    Result.Success(bonusMapper.map(bonus.data))
                }
                is Result.Error -> {
                    Result.Error(bonus.errors)
                }
            }
            return@withContext listOf(
                mGrade,
                mRefill,
                mProfit,
                mBonus
            )
        }
    }

}