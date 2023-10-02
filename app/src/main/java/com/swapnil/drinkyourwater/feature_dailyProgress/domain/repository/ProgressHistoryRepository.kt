package com.swapnil.drinkyourwater.feature_dailyProgress.domain.repository

import com.swapnil.drinkyourwater.feature_dailyProgress.domain.model.ProgressHistory
import kotlinx.coroutines.flow.Flow

interface ProgressHistoryRepository {

    suspend fun addProgress(dailyProgressHistory: ProgressHistory)

    fun getTodayProgress(): Flow<List<ProgressHistory>>

}