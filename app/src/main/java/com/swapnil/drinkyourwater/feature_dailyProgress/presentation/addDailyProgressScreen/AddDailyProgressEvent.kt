package com.swapnil.drinkyourwater.feature_dailyProgress.presentation.addDailyProgressScreen

import androidx.compose.ui.focus.FocusState

sealed class AddDailyProgressEvent{
    data class OnTargetTextChange(val value: String): AddDailyProgressEvent()
    data class OnTargetUnitChange(val unit: WaterUnits): AddDailyProgressEvent()
    data class ChangeTargetFocus(val focusState: FocusState): AddDailyProgressEvent()

    data class OnChunkTextChange(val value: String): AddDailyProgressEvent()
    data class OnChunkUnitChange(val unit: WaterUnits): AddDailyProgressEvent()
    data class ChangeChunkFocus(val focusState: FocusState): AddDailyProgressEvent()

    object OnSubmit: AddDailyProgressEvent()
}
