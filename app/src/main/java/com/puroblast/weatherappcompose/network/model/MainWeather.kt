package com.puroblast.weatherappcompose.network.model


data class MainWeather(
    val feelsLike: Double,
    val humidity: Int,
    val pressure: Int,
    val temp: Double,
    val tempMax: Double,
    val tempMin: Double
)