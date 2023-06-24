package com.puroblast.weatherappcompose.repository


import com.puroblast.weatherappcompose.network.response.DetailedData
import com.puroblast.weatherappcompose.network.response.ForeCastData


interface WeatherRepository {

    suspend fun collectWeatherData(q: String, appId: String, units: String): Result<DetailedData>

    suspend fun collectForecastData(q: String, appId: String, units: String): Result<ForeCastData>

}