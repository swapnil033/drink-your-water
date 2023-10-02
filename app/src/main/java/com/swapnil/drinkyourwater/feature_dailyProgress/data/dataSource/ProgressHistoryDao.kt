package com.swapnil.drinkyourwater.feature_dailyProgress.data.dataSource

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.swapnil.drinkyourwater.feature_dailyProgress.domain.model.ProgressHistory
import com.swapnil.drinkyourwater.feature_dailyProgress.domain.util.getDateWithoutTime
import kotlinx.coroutines.flow.Flow
import java.util.Date

@Dao
interface ProgressHistoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addProgress(dailyProgressHistory: ProgressHistory)

    @Query("SELECT * FROM daily_progress_history WHERE date == :date")
    fun getTodayProgress(date: Date = Date().getDateWithoutTime()): Flow<List<ProgressHistory>>

}