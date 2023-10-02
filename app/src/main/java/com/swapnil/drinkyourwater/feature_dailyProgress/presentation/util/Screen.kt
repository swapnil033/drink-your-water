package com.swapnil.drinkyourwater.feature_dailyProgress.presentation.util

sealed class Screen(val route: String){
    object Splash: Screen("splash_screen")
    object DailyProgress: Screen("daily_progress_screen")
    object AddDailyProgress: Screen("add_daily_progress_screen")
}
