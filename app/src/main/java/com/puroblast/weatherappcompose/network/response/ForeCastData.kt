package com.puroblast.weatherappcompose.network.response

import com.google.gson.annotations.SerializedName
import com.puroblast.weatherappcompose.network.model.SingleWeatherForeCast


data class ForeCastData(
    @SerializedName("list") val list: List<SingleWeatherForeCast>,
)
