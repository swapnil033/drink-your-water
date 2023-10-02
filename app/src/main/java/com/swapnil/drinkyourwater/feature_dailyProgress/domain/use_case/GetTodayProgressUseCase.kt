package com.swapnil.drinkyourwater.feature_dailyProgress.domain.use_case

import com.swapnil.drinkyourwater.feature_dailyProgress.domain.model.ProgressHistory
import com.swapnil.drinkyourwater.feature_dailyProgress.domain.repository.ProgressHistoryRepository
import kotlinx.coroutines.flow.Flow

class GetTodayProgressUseCase(
    private val repository: ProgressHistoryRepository
) {

    operator fun invoke(): Flow<List<ProgressHistory>> = repository.getTodayProgress()
}