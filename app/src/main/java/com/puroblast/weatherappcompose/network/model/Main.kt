package com.puroblast.weatherappcompose.network.model

import com.google.gson.annotations.SerializedName

data class Main(
    val feelsLike: Double,
    val humidity: Int,
    val pressure: Int,
    val temp: Double,
    val tempMax: Double,
    val tempMin: Double
)