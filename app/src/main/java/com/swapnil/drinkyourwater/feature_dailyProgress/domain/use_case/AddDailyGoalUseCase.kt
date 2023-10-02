package com.swapnil.drinkyourwater.feature_dailyProgress.domain.use_case

import com.swapnil.drinkyourwater.feature_dailyProgress.domain.model.DailyGoal
import com.swapnil.drinkyourwater.feature_dailyProgress.domain.model.DailyGoalException
import com.swapnil.drinkyourwater.feature_dailyProgress.domain.repository.DailyProgressRepository
import com.swapnil.drinkyourwater.feature_dailyProgress.presentation.addDailyProgressScreen.WaterUnits
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class AddDailyGoalUseCase(
    private val repository: DailyProgressRepository
) {

    suspend operator fun invoke(
        dailyGoal: DailyGoal,
        chunksUnit: WaterUnits,
        targetUnit: WaterUnits
    ){

        if (dailyGoal.target == 0)
            throw DailyGoalException("enter target")

        if (dailyGoal.chunks == 0)
            throw DailyGoalException("enter chunks")

        dailyGoal.target = convertToML(dailyGoal.target, targetUnit)
        dailyGoal.chunks = convertToML(dailyGoal.chunks, chunksUnit)

        repository.addGoal(dailyGoal)
    }

    private fun convertToML(value: Int, unit: WaterUnits): Int {
        return when(unit){
            WaterUnits.Litter -> value * 1000
            WaterUnits.MilliLiter -> value
        }
    }

}