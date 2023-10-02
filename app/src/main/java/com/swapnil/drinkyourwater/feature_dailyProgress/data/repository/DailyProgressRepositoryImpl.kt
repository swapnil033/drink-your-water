package com.swapnil.drinkyourwater.feature_dailyProgress.data.repository

import com.swapnil.drinkyourwater.feature_dailyProgress.data.dataSource.DailyGoalDao
import com.swapnil.drinkyourwater.feature_dailyProgress.domain.model.DailyGoal
import com.swapnil.drinkyourwater.feature_dailyProgress.domain.repository.DailyProgressRepository
import kotlinx.coroutines.flow.Flow

class DailyProgressRepositoryImpl(
    private val dao: DailyGoalDao
): DailyProgressRepository {
    override suspend fun addGoal(dailyGoal: DailyGoal) {
        dao.addGoal(dailyGoal)
    }

    override fun getGoalData(): Flow<List<DailyGoal>> {
        return dao.getGoalData()
    }
}