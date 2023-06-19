package com.puroblast.weatherappcompose.network

import com.puroblast.weatherappcompose.network.response.WeatherData
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherInterface {

    @GET("data/2.5/weather")
    suspend fun getWeatherData(
        @Query("q") q : String,
        @Query("APPID") appId : String
    ) : WeatherData

}