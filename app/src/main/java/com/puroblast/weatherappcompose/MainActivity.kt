package com.puroblast.weatherappcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.puroblast.weatherappcompose.features.splashscreen.SplashScreen
import com.puroblast.weatherappcompose.features.weatherscreen.presentation.WeatherState
import com.puroblast.weatherappcompose.features.weatherscreen.presentation.WeatherViewModel
import com.puroblast.weatherappcompose.ui.theme.WeatherAppComposeTheme
import com.puroblast.weatherappcompose.features.Routes
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel: WeatherViewModel by viewModels()
        viewModel.collectData()
        setContent {
            val weatherState by viewModel.state.collectAsState()
            WeatherAppComposeTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController ,
                    startDestination = Routes.SPLASH_SCREEN
                ) {
                        composable(route = Routes.SPLASH_SCREEN) {
                            SplashScreen(navController)
                        }
                        composable(route = Routes.WEATHER_SCREEN) {
                            Kekw(modifier = Modifier.fillMaxSize(), viewModel, weatherState)
                        }
                }
            }
        }
    }

    @Composable
    fun Kekw(modifier: Modifier, viewModel: WeatherViewModel, weatherState: WeatherState) {
        Box(modifier = modifier.fillMaxSize().background(Color.White)) {
            Text(text = weatherState.temperature.toString())
        }
    }
}
