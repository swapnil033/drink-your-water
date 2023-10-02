package com.swapnil.drinkyourwater.feature_dailyProgress.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "daily_goal")
data class DailyGoal(
    @PrimaryKey
    var id: Int,
    var target: Int,
    var chunks: Int,
)


class GoalNotFoundException(message: String = "goal not found. add your goal first"): java.lang.Exception(message)
class DailyGoalException(message: String): java.lang.Exception(message)
