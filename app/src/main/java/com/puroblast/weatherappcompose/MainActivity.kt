package com.puroblast.weatherappcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.puroblast.weatherappcompose.features.bottomnavbar.BottomNavItem
import com.puroblast.weatherappcompose.features.bottomnavbar.BottomNavigationBar
import com.puroblast.weatherappcompose.features.splashscreen.SplashScreen
import com.puroblast.weatherappcompose.features.weatherscreen.presentation.WeatherViewModel
import com.puroblast.weatherappcompose.ui.theme.WeatherAppComposeTheme
import com.puroblast.weatherappcompose.features.Routes
import com.puroblast.weatherappcompose.features.bottomnavbar.currentRoute
import com.puroblast.weatherappcompose.features.weatherscreen.ui.WeatherScreen
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val weatherViewModel: WeatherViewModel by viewModels()

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        weatherViewModel.collectData()

        setContent {
            WeatherAppComposeTheme {
                val navController = rememberNavController()
                Scaffold(bottomBar = {
                    if (currentRoute(navController) != Routes.SPLASH_SCREEN) {
                        BottomNavigationBar(
                            items = listOf(
                                BottomNavItem(
                                    "Weather",
                                    Routes.WEATHER_SCREEN,
                                    painterResource(id = R.drawable.baseline_timer_24)
                                ),
                                BottomNavItem(
                                    "Weather",
                                    Routes.SPLASH_SCREEN,
                                    painterResource(id = R.drawable.baseline_timer_24)
                                ),
                                BottomNavItem(
                                    "Weather",
                                    Routes.WEATHER_SCREEN,
                                    painterResource(id = R.drawable.baseline_timer_24)
                                )
                            ),
                            navController = navController,
                            onItemClick = { navController.navigate(it.route) })
                    }

                }, modifier = Modifier.fillMaxWidth()) { paddingValues ->
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(bottom = paddingValues.calculateBottomPadding())
                    ) {
                        NavigationGraph(navController = navController)
                    }
                }
            }
        }
    }


    @Composable
    fun NavigationGraph(navController: NavHostController) {

        NavHost(
            navController = navController,
            startDestination = Routes.SPLASH_SCREEN
        ) {
            composable(route = Routes.SPLASH_SCREEN) {
                SplashScreen(navController)
            }
            composable(route = Routes.WEATHER_SCREEN) {
                WeatherScreen(weatherViewModel)
            }
        }
    }

}


