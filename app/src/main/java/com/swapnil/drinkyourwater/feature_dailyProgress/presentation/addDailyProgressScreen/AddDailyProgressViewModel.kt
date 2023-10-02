package com.swapnil.drinkyourwater.feature_dailyProgress.presentation.addDailyProgressScreen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.swapnil.drinkyourwater.feature_dailyProgress.domain.model.DailyGoal
import com.swapnil.drinkyourwater.feature_dailyProgress.domain.model.DailyGoalException
import com.swapnil.drinkyourwater.feature_dailyProgress.domain.use_case.LocalUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddDailyProgressViewModel @Inject constructor(
    private val localUseCases: LocalUseCases
) : ViewModel() {

    private val _target = mutableStateOf(EditTextState( unit = WaterUnits.Litter ))
    val target: State<EditTextState> = _target

    private val _chunk = mutableStateOf(EditTextState( unit = WaterUnits.MilliLiter ))
    val chunk: State<EditTextState> = _chunk

    private val _uiEvent = MutableSharedFlow<UiEvent>()
    val uiEvent = _uiEvent

    fun onEvent(event: AddDailyProgressEvent){
        when(event){
            is AddDailyProgressEvent.ChangeChunkFocus -> {

            }
            is AddDailyProgressEvent.ChangeTargetFocus -> {

            }
            is AddDailyProgressEvent.OnChunkTextChange -> {
                _chunk.value = chunk.value.copy(text = event.value)
            }
            is AddDailyProgressEvent.OnTargetTextChange -> {
                _target.value = target.value.copy(text = event.value)
            }
            is AddDailyProgressEvent.OnChunkUnitChange -> {
                _chunk.value = chunk.value.copy(unit = event.unit)
            }
            is AddDailyProgressEvent.OnTargetUnitChange -> {
                _target.value = target.value.copy(unit = event.unit)
            }

            is AddDailyProgressEvent.OnSubmit -> {
                localUseCases.getDailyGoalUseCase().onEach {
                    val list = it

                    try {
                        val dailyGoal = DailyGoal(
                            id = if (list.isEmpty()) 1 else list.first().id,
                            target = (getIntValue(target.value.text)),
                            chunks = (getIntValue(chunk.value.text)),
                        )

                        localUseCases.addDailyGoalUseCase(
                            dailyGoal = dailyGoal,
                            targetUnit = target.value.unit,
                            chunksUnit = chunk.value.unit,
                        )
                        _uiEvent.emit(UiEvent.SaveGoal)
                    }catch (e: DailyGoalException){
                        _uiEvent.emit(UiEvent.ShowSnackBar(message = e.message ?: "Note didn't save"))
                    }


                }.launchIn(viewModelScope)
            }

        }
    }

    private fun getIntValue(text: String?): Int{
        if (text.isNullOrBlank())
            return 0
        return text.toInt()
    }

    sealed class UiEvent{
        data class ShowSnackBar(val message: String): UiEvent()
        object SaveGoal: UiEvent()
    }

}

sealed class WaterUnits(val unit:String){
    object Litter: WaterUnits("litter")
    object MilliLiter: WaterUnits("milliliter")
}