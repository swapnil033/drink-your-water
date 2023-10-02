package com.swapnil.drinkyourwater.feature_dailyProgress.domain.use_case

import com.swapnil.drinkyourwater.feature_dailyProgress.domain.model.DailyGoal
import com.swapnil.drinkyourwater.feature_dailyProgress.domain.model.GoalNotFoundException
import com.swapnil.drinkyourwater.feature_dailyProgress.domain.model.ProgressAlreadyExistException
import com.swapnil.drinkyourwater.feature_dailyProgress.domain.model.ProgressHistory
import com.swapnil.drinkyourwater.feature_dailyProgress.domain.repository.ProgressHistoryRepository
import com.swapnil.drinkyourwater.feature_dailyProgress.domain.repository.DailyProgressRepository
import com.swapnil.drinkyourwater.feature_dailyProgress.domain.util.getDateWithoutTime
import java.util.Date

class AddNewProgressUseCase(
    private val progressHistoryRepository: ProgressHistoryRepository,
    private val dailyProgressRepository: DailyProgressRepository,
) {


    suspend operator fun invoke(progressHistory : ProgressHistory){
        progressHistoryRepository.addProgress(progressHistory)
    }

    suspend operator fun invoke(){
        progressHistoryRepository.getTodayProgress().collect{
            if (it.isNotEmpty())
                throw ProgressAlreadyExistException(progress = it)
            getGoal()
        }
    }


    private suspend fun getGoal() {
        dailyProgressRepository.getGoalData().collect {
            if (it.isEmpty())
                throw GoalNotFoundException()
            addNewProgress(it.first())
        }
    }

    private suspend fun addNewProgress(dailyGoal: DailyGoal) {
        val target = dailyGoal.target / dailyGoal.chunks
        progressHistoryRepository.addProgress(
            ProgressHistory(
                target = target,
                progress = 0,
                date = Date().getDateWithoutTime()
            )
        )
    }

}