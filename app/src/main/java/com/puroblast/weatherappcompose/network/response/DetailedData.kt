package com.puroblast.weatherappcompose.network.response

import com.puroblast.weatherappcompose.utils.EMPTY_STRING

data class DetailedData(

    val weatherId: Int = 0,
    val cityName: String = EMPTY_STRING,
    val description: String = EMPTY_STRING,
    val temperature: Double = 0.00,
    val minimalTemperature: Double = 0.00,
    val maximalTemperature: Double = 0.00,
    val feelsLike: Double = 0.00,
    val humidity: Int = 0,
    val pressure: Int = 0,
    val windSpeed: Double = 0.00,
    val windDegree: Int = 0,
    val visibility: Int = 0,
    val cloudiness: Int = 0,
    val sunsetTime: Long = 0,
    val sunriseTime: Long = 0
)
