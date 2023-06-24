package com.puroblast.weatherappcompose.network.response

import com.puroblast.weatherappcompose.network.model.Clouds
import com.puroblast.weatherappcompose.network.model.Coord
import com.puroblast.weatherappcompose.network.model.MainWeather
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
    val main: MainWeather,
    val name: String,
    val sys: Sys,
    val timezone: Int,
    val visibility: Int,
    val weather: List<Weather>,
    val wind: Wind
) {
    fun getDetailedData(): DetailedData {
        return DetailedData(
            weatherId = this.weather[0].id,
            cityName = this.name,
            description = this.weather[0].description,
            temperature = this.main.temp,
            minimalTemperature = this.main.tempMin,
            maximalTemperature = this.main.tempMax,
            feelsLike = this.main.feelsLike,
            humidity = this.main.humidity,
            pressure = this.main.pressure,
            windSpeed = this.wind.speed,
            windDegree = this.wind.deg,
            visibility = this.visibility,
            cloudiness = this.clouds.all,
            sunsetTime = this.sys.sunset,
            sunriseTime = this.sys.sunrise
        )
    }


}
