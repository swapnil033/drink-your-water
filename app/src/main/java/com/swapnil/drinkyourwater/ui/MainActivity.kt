package com.swapnil.drinkyourwater.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavOptions
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.swapnil.drinkyourwater.feature_dailyProgress.presentation.addDailyProgressScreen.AddDailyProgressScreen
import com.swapnil.drinkyourwater.feature_dailyProgress.presentation.dailyProgressListScreen.DailyProgressScreen
import com.swapnil.drinkyourwater.feature_dailyProgress.presentation.splashScreen.SplashScreen
import com.swapnil.drinkyourwater.feature_dailyProgress.presentation.util.Screen
import com.swapnil.drinkyourwater.ui.theme.DrinkYourWaterTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DrinkYourWaterTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavigation()
                }
            }
        }
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route,
        builder = {

            composable(route = Screen.Splash.route){
                SplashScreen(
                    nextScreen = { isGoalExists ->
                        if (isGoalExists)
                            navController.navigate(Screen.DailyProgress.route)
                        else
                            navController.navigate(Screen.AddDailyProgress.route)
                    }
                )
            }
            composable(route = Screen.DailyProgress.route){
                DailyProgressScreen()
            }
            composable(route = Screen.AddDailyProgress.route){
                AddDailyProgressScreen(
                    onSubmit = {
                        navController.navigate(Screen.DailyProgress.route)
                    }
                )
            }

        }
    )
}

/*
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    DrinkYourWaterTheme {
        Greeting("Android")
    }
}*/
