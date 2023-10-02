package com.swapnil.drinkyourwater.feature_dailyProgress.presentation.addDailyProgressScreen

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.swapnil.drinkyourwater.feature_dailyProgress.presentation.addDailyProgressScreen.component.DropDownText
import com.swapnil.drinkyourwater.feature_dailyProgress.presentation.addDailyProgressScreen.component.TextFieldWithDropDown
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AddDailyProgressScreen(
    viewModel: AddDailyProgressViewModel = hiltViewModel(),
    onSubmit: () -> Unit
) {
    val snackBarHostState = remember { SnackbarHostState() }

    val target = viewModel.target.value
    val chunk = viewModel.chunk.value
    
    LaunchedEffect(key1 = true){
        viewModel.uiEvent.collectLatest {
            when(it){
                AddDailyProgressViewModel.UiEvent.SaveGoal -> {
                    onSubmit()
                }
                is AddDailyProgressViewModel.UiEvent.ShowSnackBar -> {
                    snackBarHostState.showSnackbar(message = it.message)
                }
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "Add your goal") })
        },
        snackbarHost = { SnackbarHost(snackBarHostState) },
    ) {paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(10.dp)
        ) {

            TextFieldWithDropDown(
                editTextState = target,
                onValueChange = {
                    viewModel.onEvent(AddDailyProgressEvent.OnTargetTextChange(it))
                },
                onUnitChange = {
                    viewModel.onEvent(AddDailyProgressEvent.OnTargetUnitChange(it))
                },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(10.dp))

            TextFieldWithDropDown(
                editTextState = chunk,
                onValueChange = {
                    viewModel.onEvent(AddDailyProgressEvent.OnChunkTextChange(it))
                },
                onUnitChange = {
                    viewModel.onEvent(AddDailyProgressEvent.OnChunkUnitChange(it))
                },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(20.dp))

            Button(onClick = { viewModel.onEvent(AddDailyProgressEvent.OnSubmit) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Submit")
            }

        }
    }
}

