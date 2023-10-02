package com.swapnil.drinkyourwater.feature_dailyProgress.domain.repository

import com.swapnil.drinkyourwater.feature_dailyProgress.domain.model.DailyGoal
import kotlinx.coroutines.flow.Flow

interface DailyProgressRepository {

    suspend fun addGoal(dailyGoal: DailyGoal)

    fun getGoalData(): Flow<List<DailyGoal>>

}