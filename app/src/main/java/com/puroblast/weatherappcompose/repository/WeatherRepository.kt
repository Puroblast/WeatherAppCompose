package com.puroblast.weatherappcompose.repository


import com.puroblast.weatherappcompose.network.response.DetailedData


interface WeatherRepository {

    suspend fun collectWeatherData(q: String, appId: String , units: String): Result<DetailedData>

}