package com.swapnil.drinkyourwater.feature_dailyProgress.domain.use_case

import com.swapnil.drinkyourwater.feature_dailyProgress.domain.model.DailyGoal
import com.swapnil.drinkyourwater.feature_dailyProgress.domain.repository.DailyProgressRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetDailyGoalUseCase(
    private val repository: DailyProgressRepository
) {

    operator fun invoke(): Flow<List<DailyGoal>>  {
        return repository.getGoalData()
    }

}