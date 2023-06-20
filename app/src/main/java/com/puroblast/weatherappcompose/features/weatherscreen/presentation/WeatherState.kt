package com.puroblast.weatherappcompose.features.weatherscreen.presentation

data class WeatherState(

    val temperature: Double = 0.00,
    val humidity: Int = 0,
    val pressure: Int = 0,
    val windSpeed: Int = 0,
    val icon: String = "01d.png"

)
