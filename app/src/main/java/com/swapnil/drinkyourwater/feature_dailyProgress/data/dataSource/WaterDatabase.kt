package com.swapnil.drinkyourwater.feature_dailyProgress.data.dataSource

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.swapnil.drinkyourwater.feature_dailyProgress.domain.model.DailyGoal
import com.swapnil.drinkyourwater.feature_dailyProgress.domain.model.ProgressHistory
import com.swapnil.noteapp.feature_note.domain.util.Converters

@Database(
    entities =
    [
        DailyGoal::class,
        ProgressHistory::class,
    ],
    version = 1
)
@TypeConverters(
    value = [Converters::class]
)
abstract class WaterDatabase: RoomDatabase() {

    abstract val dailyGoalDao: DailyGoalDao
    abstract val progressHistoryDao: ProgressHistoryDao

    companion object{
        val DATABASE_NAME = "water_db"
    }

}