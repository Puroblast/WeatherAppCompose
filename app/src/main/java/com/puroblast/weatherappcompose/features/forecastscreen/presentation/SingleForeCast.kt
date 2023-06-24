package com.puroblast.weatherappcompose.features.forecastscreen.presentation

import com.puroblast.weatherappcompose.utils.EMPTY_STRING

data class SingleForeCast(
    val date: String = EMPTY_STRING, val temp: Double = 0.00
)
