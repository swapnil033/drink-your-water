package com.swapnil.drinkyourwater.feature_dailyProgress.data.dataSource

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.swapnil.drinkyourwater.feature_dailyProgress.domain.model.DailyGoal
import kotlinx.coroutines.flow.Flow

@Dao
interface DailyGoalDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addGoal(dailyGoal: DailyGoal)

    @Query("SELECT * FROM daily_goal")
    fun getGoalData(): Flow<List<DailyGoal>>

}