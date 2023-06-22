package com.puroblast.weatherappcompose.features

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.puroblast.weatherappcompose.R
import com.puroblast.weatherappcompose.features.bottomnavbar.BottomNavItem
import com.puroblast.weatherappcompose.features.bottomnavbar.BottomNavigationBar
import com.puroblast.weatherappcompose.features.sharedviewmodelscreens.detailsscreen.ui.DetailsScreen
import com.puroblast.weatherappcompose.features.splashscreen.SplashScreen
import com.puroblast.weatherappcompose.features.sharedviewmodelscreens.WeatherSharedViewModel
import com.puroblast.weatherappcompose.features.sharedviewmodelscreens.weatherscreen.ui.WeatherScreen
import com.puroblast.weatherappcompose.utils.extension.sharedViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()

    Scaffold(bottomBar = {
        if (navBackStackEntry?.destination?.route != Routes.SPLASH_SCREEN) {
            BottomNavigationBar(
                items = listOf(
                    BottomNavItem(
                        "Weather",
                        Routes.WEATHER_SCREEN,
                        painterResource(id = R.drawable.baseline_timer_24)
                    ),
                    BottomNavItem(
                        "Details",
                        Routes.DETAILS_SCREEN,
                        painterResource(id = R.drawable.baseline_timer_24)
                    ),
                    BottomNavItem(
                        "Weather",
                        Routes.WEATHER_SCREEN,
                        painterResource(id = R.drawable.baseline_timer_24)
                    )
                ),
                onItemClick = { navController.navigate(it.route) },
                navBackStackEntry = navBackStackEntry
            )
        }

    }, modifier = Modifier.fillMaxWidth()) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = paddingValues.calculateBottomPadding())
        ) {
            NavHost(navController = navController, startDestination = Routes.MAIN_SCREEN) {
                navigation(startDestination = Routes.SPLASH_SCREEN, route = Routes.MAIN_SCREEN) {
                    composable(Routes.SPLASH_SCREEN) {
                        val viewModel = it.sharedViewModel<WeatherSharedViewModel>(navController)
                        viewModel.collectData()
                        SplashScreen(navController)
                    }
                    composable(Routes.WEATHER_SCREEN) {
                        val viewModel = it.sharedViewModel<WeatherSharedViewModel>(navController)
                        LaunchedEffect(key1 = true) {
                            viewModel.setupWeatherState()
                        }
                        val weatherState = viewModel.weatherState.collectAsState().value
                        WeatherScreen(weatherState)
                    }
                    composable(Routes.DETAILS_SCREEN) {
                        val viewModel = it.sharedViewModel<WeatherSharedViewModel>(navController)
                        LaunchedEffect(key1 = true) {
                            viewModel.setupDetailsState()
                        }
                        val detailsState = viewModel.detailsState.collectAsState().value
                        DetailsScreen(detailsState)
                    }
                }
            }
        }
    }
}