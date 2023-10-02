package com.swapnil.drinkyourwater.feature_dailyProgress.presentation.addDailyProgressScreen

import androidx.compose.ui.focus.FocusState

data class EditTextState(
    var text: String = "",
    var hint: String = "",
    var unit : WaterUnits = WaterUnits.MilliLiter,
    var showHint: Boolean = true
)
