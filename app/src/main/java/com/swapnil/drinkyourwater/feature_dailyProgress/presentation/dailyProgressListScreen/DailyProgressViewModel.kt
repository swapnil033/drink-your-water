package com.swapnil.drinkyourwater.feature_dailyProgress.presentation.dailyProgressListScreen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.swapnil.drinkyourwater.feature_dailyProgress.domain.model.GoalNotFoundException
import com.swapnil.drinkyourwater.feature_dailyProgress.domain.model.ProgressAlreadyExistException
import com.swapnil.drinkyourwater.feature_dailyProgress.domain.model.ProgressHistory
import com.swapnil.drinkyourwater.feature_dailyProgress.domain.model.ProgressNotFoundException
import com.swapnil.drinkyourwater.feature_dailyProgress.domain.use_case.LocalUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DailyProgressViewModel @Inject constructor(
    private val localUseCases: LocalUseCases
): ViewModel() {

    private val _progressDate = mutableStateOf(ProgressHistory())
    val progressDate: State<ProgressHistory> = _progressDate

    private val _progress = mutableStateOf<Float>(0f)
    val progress: State<Float> = _progress

    init {
        try {
            calCount()
        }catch (e: ProgressNotFoundException){
            addNewProgress()
        }
    }

    private fun addNewProgress() {
        viewModelScope.launch {
            try {
                localUseCases.addNewProgressUseCase()
            }catch (e: ProgressAlreadyExistException){
                e.printStackTrace()
            }catch (e: GoalNotFoundException){
                e.printStackTrace()
            }finally {
                calCount()
            }
        }
    }

    private fun calCount() {
        localUseCases.getTodayProgressUseCase().onEach {
            if (it.isEmpty())
                throw ProgressNotFoundException()
            
            val data = it.first()
            _progressDate.value = data

            _progress.value = (data.progress.toFloat() / data.target.toFloat())

        }.launchIn(viewModelScope)
    }

    fun onEvent(event: DailyProgressScreenEvent) {
        when(event){
            DailyProgressScreenEvent.IncreaseProgress -> {
                increaseProgress()
            }
        }
    }

    private fun increaseProgress() {

        viewModelScope.launch {

            val addedVal = if (progressDate.value.progress == progressDate.value.target)
                progressDate.value.progress
            else
                progressDate.value.progress + 1

            _progressDate.value = progressDate.value.copy(progress = addedVal)

            localUseCases.addNewProgressUseCase(progressDate.value)
            calCount()
        }
    }
}