package com.swapnil.drinkyourwater.feature_dailyProgress.presentation.splashScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.swapnil.drinkyourwater.feature_dailyProgress.domain.model.GoalNotFoundException
import com.swapnil.drinkyourwater.feature_dailyProgress.domain.model.ProgressAlreadyExistException
import com.swapnil.drinkyourwater.feature_dailyProgress.domain.use_case.LocalUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val localUseCases: LocalUseCases
) : ViewModel() {

    fun nextScreen(nextScreen: (Boolean) -> Unit){
        localUseCases.getDailyGoalUseCase().onEach {
            delay(500)
            //delay(3000)

            try {
                localUseCases.addNewProgressUseCase()
            }catch (e: ProgressAlreadyExistException){
                e.printStackTrace()
            }catch (e: GoalNotFoundException){
                e.printStackTrace()
            }finally {
                nextScreen(it.isNotEmpty())
            }

        }.launchIn(viewModelScope)
    }

}