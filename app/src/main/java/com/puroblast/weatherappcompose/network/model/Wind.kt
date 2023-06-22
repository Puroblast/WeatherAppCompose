package com.puroblast.weatherappcompose.network.model

import kotlinx.serialization.Serializable


data class Wind(
    val deg: Int,
    val speed: Double
)