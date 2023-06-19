package com.puroblast.weatherappcompose.network

import com.puroblast.weatherappcompose.utils.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object WeatherClient {



    val api : WeatherInterface by lazy {
        Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherInterface::class.java)
    }
}