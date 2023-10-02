package com.swapnil.drinkyourwater.feature_dailyProgress.domain.util

import java.util.Calendar
import java.util.Date

//fun getTodayDateWithoutTime(): Date = Date().getDateWithoutTime()


fun Date.getDateWithoutTime(): Date{
    val cal: Calendar = Calendar.getInstance()
    cal.time = this

    cal.set(Calendar.HOUR, 0)
    cal.set(Calendar.MINUTE, 0)
    cal.set(Calendar.SECOND, 0)
    cal.set(Calendar.MILLISECOND, 0)
    return cal.time
}