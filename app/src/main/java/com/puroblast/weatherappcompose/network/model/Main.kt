package com.puroblast.weatherappcompose.network.model


import kotlinx.serialization.Serializable


data class Main(
    val feelsLike: Double,
    val humidity: Int,
    val pressure: Int,
    val temp: Double,
    val tempMax: Double,
    val tempMin: Double
)