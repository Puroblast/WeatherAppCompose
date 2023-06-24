package com.puroblast.weatherappcompose.network.model

import com.google.gson.annotations.SerializedName

data class SingleWeatherForeCast(
    @SerializedName("main") val main: MainForecastWeather,
    @SerializedName("dt_txt") val dtTxt: String
)
