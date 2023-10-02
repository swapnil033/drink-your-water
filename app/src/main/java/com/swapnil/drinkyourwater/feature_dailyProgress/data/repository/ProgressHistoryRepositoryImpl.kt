package com.swapnil.drinkyourwater.feature_dailyProgress.data.repository

import com.swapnil.drinkyourwater.feature_dailyProgress.data.dataSource.ProgressHistoryDao
import com.swapnil.drinkyourwater.feature_dailyProgress.domain.model.ProgressHistory
import com.swapnil.drinkyourwater.feature_dailyProgress.domain.repository.ProgressHistoryRepository
import kotlinx.coroutines.flow.Flow

class ProgressHistoryRepositoryImpl(
    private val dao: ProgressHistoryDao
): ProgressHistoryRepository {
    override suspend fun addProgress(dailyProgressHistory: ProgressHistory) {
        dao.addProgress(dailyProgressHistory)
    }

    override fun getTodayProgress(): Flow<List<ProgressHistory>> {
        return dao.getTodayProgress()
    }
}