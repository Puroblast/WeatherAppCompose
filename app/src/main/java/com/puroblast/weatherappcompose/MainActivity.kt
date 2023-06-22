package com.puroblast.weatherappcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.puroblast.weatherappcompose.features.MainScreen
import com.puroblast.weatherappcompose.ui.theme.WeatherAppComposeTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherAppComposeTheme {
                MainScreen()
            }
        }
    }

}


