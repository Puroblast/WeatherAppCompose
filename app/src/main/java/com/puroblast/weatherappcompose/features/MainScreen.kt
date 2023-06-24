package com.puroblast.weatherappcompose.features

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.snap
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.navigation
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.puroblast.weatherappcompose.R
import com.puroblast.weatherappcompose.features.bottomnavbar.BottomNavItem
import com.puroblast.weatherappcompose.features.bottomnavbar.BottomNavigationBar
import com.puroblast.weatherappcompose.features.forecastscreen.presentation.ForecastViewModel
import com.puroblast.weatherappcompose.features.forecastscreen.ui.ForecastScreen
import com.puroblast.weatherappcompose.features.sharedviewmodelscreens.detailsscreen.ui.DetailsScreen
import com.puroblast.weatherappcompose.features.splashscreen.SplashScreen
import com.puroblast.weatherappcompose.features.sharedviewmodelscreens.WeatherSharedViewModel
import com.puroblast.weatherappcompose.features.sharedviewmodelscreens.weatherscreen.ui.WeatherScreen
import com.puroblast.weatherappcompose.utils.extension.sharedViewModel


@OptIn(ExperimentalAnimationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val navController = rememberAnimatedNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val foreCastViewModel: ForecastViewModel = hiltViewModel()

    Scaffold(bottomBar = {
        if (navBackStackEntry?.destination?.route != Routes.SPLASH_SCREEN) {
            BottomNavigationBar(
                items = listOf(
                    BottomNavItem(
                        "Weather",
                        Routes.WEATHER_SCREEN,
                        painterResource(id = R.drawable.baseline_cloud_24)
                    ), BottomNavItem(
                        "Details",
                        Routes.DETAILS_SCREEN,
                        painterResource(id = R.drawable.baseline_density_small_24)
                    ), BottomNavItem(
                        "Forecast",
                        Routes.FORECAST_SCREEN,
                        painterResource(id = R.drawable.baseline_timer_24)
                    )
                ), onItemClick = {
                    navController.navigate(it.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }, navBackStackEntry = navBackStackEntry
            )
        }

    }, modifier = Modifier.fillMaxWidth()) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = paddingValues.calculateBottomPadding())
        ) {
            AnimatedNavHost(navController = navController,
                startDestination = Routes.MAIN_SCREEN,
                enterTransition = {
                    slideInHorizontally(
                        initialOffsetX = { width -> width }, animationSpec = snap(0)
                    )
                },
                exitTransition = {
                    slideOutHorizontally(
                        targetOffsetX = { width -> -width }, animationSpec = snap(0)
                    )
                }) {
                navigation(startDestination = Routes.SPLASH_SCREEN, route = Routes.MAIN_SCREEN) {
                    composable(Routes.SPLASH_SCREEN) {
                        val viewModel = it.sharedViewModel<WeatherSharedViewModel>(navController)
                        SplashScreen(navController)
                    }
                    composable(Routes.WEATHER_SCREEN) {
                        val viewModel = it.sharedViewModel<WeatherSharedViewModel>(navController)
                        WeatherScreen(viewModel)
                    }
                    composable(Routes.DETAILS_SCREEN) {
                        val viewModel = it.sharedViewModel<WeatherSharedViewModel>(navController)
                        DetailsScreen(viewModel)
                    }
                    composable(Routes.FORECAST_SCREEN) {
                        ForecastScreen(foreCastViewModel)
                    }
                }
            }
        }
    }
}