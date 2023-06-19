package com.puroblast.weatherappcompose.repository


import com.puroblast.weatherappcompose.network.WeatherClient
import com.puroblast.weatherappcompose.network.response.WeatherData
import com.puroblast.weatherappcompose.utils.extension.runCatchingCancellable


class WeatherRepositoryImpl : WeatherRepository {
    override suspend fun collectWeatherData(q: String, appId: String): Result<WeatherData> {
        val weatherData = runCatchingCancellable {
            WeatherClient.api.getWeatherData(
                q,
                appId
            )
        }
        return weatherData
    }
}

