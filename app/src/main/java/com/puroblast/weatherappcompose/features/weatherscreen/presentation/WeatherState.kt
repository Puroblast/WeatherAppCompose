package com.puroblast.weatherappcompose.features.weatherscreen.presentation

data class WeatherState(

    val weatherId : Int = 0,
    val cityName: String = "",
    val description: String = "",
    val temperature: Double = 0.00,
    val feelsLike : Double = 0.00,
    val humidity: Int = 0,
    val pressure: Int = 0,
    val windSpeed: Int = 0,
    val sunsetTime: Int = 0

)
