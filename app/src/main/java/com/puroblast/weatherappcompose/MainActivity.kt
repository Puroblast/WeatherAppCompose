package com.puroblast.weatherappcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.puroblast.weatherappcompose.network.model.RandomState
import com.puroblast.weatherappcompose.features.weatherscreen.presentation.WeatherViewModel
import com.puroblast.weatherappcompose.ui.theme.WeatherAppComposeTheme
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
                Kekw(modifier = Modifier.fillMaxSize(), viewModel, weatherState)
            }
        }
    }

    @Composable
    fun Kekw(modifier: Modifier, viewModel: WeatherViewModel, weatherState: RandomState) {
        Box(modifier = modifier) {
            Text(text = weatherState.text)
        }
    }
}
