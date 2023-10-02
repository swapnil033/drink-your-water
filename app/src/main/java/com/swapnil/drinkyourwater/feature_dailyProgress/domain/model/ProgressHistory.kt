package com.swapnil.drinkyourwater.feature_dailyProgress.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "daily_progress_history")
data class ProgressHistory(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var target: Int = 0,
    var progress: Int = 0,
    val date: Date = Date(),
)

class ProgressAlreadyExistException(message: String = "progress found", progress: List<ProgressHistory>): java.lang.Exception(message)

class ProgressNotFoundException(message: String = "No progress found"): java.lang.Exception(message)
