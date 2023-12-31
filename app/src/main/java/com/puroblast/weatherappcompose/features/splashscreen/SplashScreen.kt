package com.puroblast.weatherappcompose.features.splashscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.puroblast.weatherappcompose.R
import com.puroblast.weatherappcompose.features.Routes
import com.puroblast.weatherappcompose.utils.EMPTY_STRING
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {
    Box(
        Modifier
            .fillMaxSize()
            .padding(bottom = 50.dp)
    ) {
        LaunchedEffect(key1 = true) {
            delay(1000)
            navController.navigate(Routes.WEATHER_SCREEN) {
                popUpTo(Routes.SPLASH_SCREEN) {
                    inclusive = true
                }
            }
        }

        Image(
            painter = painterResource(id = R.drawable.baseline_cloud_splash_24),
            contentDescription = EMPTY_STRING,
            modifier = Modifier
                .align(Alignment.Center)
                .size(300.dp)
        )

        CircularProgressIndicator(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .size(64.dp),
            strokeWidth = 6.dp,
            color = Color.Green
        )

    }
}
