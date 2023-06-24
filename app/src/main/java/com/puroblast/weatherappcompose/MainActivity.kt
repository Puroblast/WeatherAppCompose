package com.puroblast.weatherappcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.puroblast.weatherappcompose.features.MainScreen
import com.puroblast.weatherappcompose.features.splashscreen.SplashScreen
import com.puroblast.weatherappcompose.ui.theme.WeatherAppComposeTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)

        setContent {
            WeatherAppComposeTheme {
                MainScreen()
            }
        }
    }

}


