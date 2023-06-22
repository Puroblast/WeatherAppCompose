package com.puroblast.weatherappcompose.features.sharedviewmodelscreens.weatherscreen.presentation

import com.puroblast.weatherappcompose.utils.EMPTY_STRING

data class WeatherState(

    val weatherId: Int = 0,
    val cityName: String = EMPTY_STRING,
    val description: String = EMPTY_STRING,
    val temperature: Double = 0.00,
    val humidity: Int = 0,
    val pressure: Int = 0,
    val windSpeed: Double = 0.00,
    val sunsetTime: Int = 0,
    val sunriseTime: Int = 0,
    val clouds : Int = 0

)
