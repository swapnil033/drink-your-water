package com.swapnil.drinkyourwater.feature_dailyProgress.presentation.dailyProgressListScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.swapnil.drinkyourwater.ui.theme.DrinkYourWaterTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DailyProgressScreen(
    viewModel: DailyProgressViewModel = hiltViewModel()
) {

    val snackBarHostState = remember { SnackbarHostState() }

    //val list = viewModel.list.value

    val progress = viewModel.progress.value

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "Daily Progress") })
        },
        snackbarHost = { SnackbarHost(snackBarHostState) },
    ) { paddingValues ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(40.dp)
        ) {

            DailyProgressIndicator(
                progress = progress,
                modifier = Modifier.size(200.dp)
            )

            Spacer(modifier = Modifier.height(20.dp))
            Button(onClick = { viewModel.onEvent(DailyProgressScreenEvent.IncreaseProgress) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Add")
            }


            /*LazyColumn(){

                items(list){ dailyProgressData ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp, vertical = 10.dp)
                            .background(
                                color = MaterialTheme.colorScheme.secondaryContainer,
                                shape = RoundedCornerShape(20.dp)
                            )
                            .padding(20.dp)
                    ) {
                        Text(
                            text = dailyProgressData.unit,
                            color = MaterialTheme.colorScheme.onSecondaryContainer
                        )
                    }

                }
            }*/
        }
    }
}

@Composable
fun DailyProgressIndicator(
    progress: Float,
    modifier: Modifier = Modifier
) {

    CircularProgressIndicator(
        progress = progress,
        strokeWidth = 15.dp,
        modifier = modifier,
        trackColor = MaterialTheme.colorScheme.secondary.copy(alpha = 0.6f),
        color = MaterialTheme.colorScheme.primary,
        strokeCap  = StrokeCap.Round,
    )

}

@Preview()
@Composable
fun DailyProgressIndicatorPreview() {
    DrinkYourWaterTheme {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            DailyProgressIndicator(progress = 0.5f)
        }
    }
}