package com.puroblast.weatherappcompose.network.response

import com.puroblast.weatherappcompose.network.model.Clouds
import com.puroblast.weatherappcompose.network.model.Coord
import com.puroblast.weatherappcompose.network.model.Main
import com.puroblast.weatherappcompose.network.model.Sys
import com.puroblast.weatherappcompose.network.model.Weather
import com.puroblast.weatherappcompose.network.model.Wind

data class WeatherData(
    val base: String,
    val clouds: Clouds,
    val cod: Int,
    val coord: Coord,
    val dt: Int,
    val id: Int,
    val main: Main,
    val name: String,
    val sys: Sys,
    val timezone: Int,
    val visibility: Int,
    val weather: List<Weather>,
    val wind: Wind
)