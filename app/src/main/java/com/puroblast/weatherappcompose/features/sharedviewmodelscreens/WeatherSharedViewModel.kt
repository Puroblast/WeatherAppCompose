package com.puroblast.weatherappcompose.features.sharedviewmodelscreens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.puroblast.weatherappcompose.features.sharedviewmodelscreens.detailsscreen.presentation.DetailsState
import com.puroblast.weatherappcompose.features.sharedviewmodelscreens.weatherscreen.presentation.WeatherState
import com.puroblast.weatherappcompose.network.response.DetailedData
import com.puroblast.weatherappcompose.repository.WeatherRepository
import com.puroblast.weatherappcompose.utils.CITY
import com.puroblast.weatherappcompose.utils.TOKEN
import com.puroblast.weatherappcompose.utils.UNITS
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class WeatherSharedViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository
) : ViewModel() {

    private var _body: DetailedData = DetailedData()
    private val _weatherState = MutableStateFlow(WeatherState())
    val weatherState = _weatherState.asStateFlow()
    private val _detailsState = MutableStateFlow(DetailsState())
    val detailsState = _detailsState.asStateFlow()

    init {
        collectData()
    }

    private fun collectData() {
        viewModelScope.launch {
            _body = weatherRepository.collectWeatherData(CITY, TOKEN, UNITS)
                .getOrElse { DetailedData() }
        }
    }

    fun setupWeatherState() {
        _weatherState.value = weatherState.value.copy(
            weatherId = _body.weatherId,
            temperature = _body.temperature,
            humidity = _body.humidity,
            pressure = _body.pressure,
            windSpeed = _body.windSpeed,
            sunsetTime = _body.sunsetTime,
            sunriseTime = _body.sunriseTime,
            cityName = _body.cityName,
            description = _body.description
        )
    }

    fun setupDetailsState() {
        _detailsState.value = detailsState.value.copy(
            weatherId = _body.weatherId,
            cityName = _body.cityName,
            description = _body.description,
            temperature = _body.temperature,
            minimalTemperature = _body.minimalTemperature,
            maximalTemperature = _body.maximalTemperature,
            feelsLike = _body.feelsLike,
            humidity = _body.humidity,
            pressure = _body.pressure,
            windSpeed = _body.windSpeed,
            windDegree = _body.windDegree,
            visibility = _body.visibility,
            cloudiness = _body.cloudiness,
            sunsetTime = _body.sunsetTime,
            sunriseTime = _body.sunriseTime,
        )
    }
}