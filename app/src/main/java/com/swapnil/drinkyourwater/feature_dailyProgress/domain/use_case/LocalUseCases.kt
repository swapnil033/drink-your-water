package com.swapnil.drinkyourwater.feature_dailyProgress.domain.use_case

data class LocalUseCases(
    val addDailyGoalUseCase: AddDailyGoalUseCase,
    val getDailyGoalUseCase: GetDailyGoalUseCase,
    val getTodayProgressUseCase: GetTodayProgressUseCase,

    val addNewProgressUseCase: AddNewProgressUseCase,
)
