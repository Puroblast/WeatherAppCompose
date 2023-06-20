package com.puroblast.weatherappcompose.features.weatherscreen.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.puroblast.weatherappcompose.repository.WeatherRepository
import com.puroblast.weatherappcompose.utils.CITY
import com.puroblast.weatherappcompose.utils.TAG
import com.puroblast.weatherappcompose.utils.TOKEN
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository
) : ViewModel() {


    private val _state = MutableStateFlow(WeatherState())
    val state = _state.asStateFlow()

    fun collectData() {
        viewModelScope.launch {

            val result = weatherRepository.collectWeatherData(CITY, TOKEN)
            if (result.isSuccess) {
                val body = result.getOrNull()
                if (body != null) {
                    _state.value = _state.value.copy(
                        temperature = body.main.temp,
                        humidity = body.main.humidity,
                        pressure = body.main.pressure,
                        windSpeed = body.wind.speed,
                        icon = body.weather[0].icon
                    )
                } else {
                    Log.d(TAG, "collectData: Body is Null")
                }
            } else {
                Log.d(TAG, "collectData: Failure")
            }
        }
    }
}