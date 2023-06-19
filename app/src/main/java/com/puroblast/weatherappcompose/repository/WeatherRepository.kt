package com.puroblast.weatherappcompose.repository

import com.puroblast.weatherappcompose.network.response.WeatherData

interface WeatherRepository {

    suspend fun collectWeatherData(q: String, appId: String): Result<WeatherData>

}