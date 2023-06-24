package com.puroblast.weatherappcompose.network

import com.puroblast.weatherappcompose.network.response.ForeCastData
import com.puroblast.weatherappcompose.network.response.WeatherData
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("data/2.5/weather")
    suspend fun getWeatherData(
        @Query("q") q: String, @Query("APPID") appId: String, @Query("units") units: String
    ): WeatherData


    @GET("data/2.5/forecast")
    suspend fun getForeCastData(
        @Query("q") q: String, @Query("APPID") appId: String, @Query("units") units: String
    ): ForeCastData

}