package com.puroblast.weatherappcompose.repository


import com.puroblast.weatherappcompose.network.WeatherApi
import com.puroblast.weatherappcompose.network.response.WeatherData
import com.puroblast.weatherappcompose.utils.extension.runCatchingCancellable
import javax.inject.Inject


class WeatherRepositoryImpl @Inject constructor(
    private val weatherApi: WeatherApi,
) : WeatherRepository {
    override suspend fun collectWeatherData(q: String, appId: String , units: String): Result<WeatherData> {
        val weatherData = runCatchingCancellable {
            weatherApi.getWeatherData(
                q,
                appId,
                units
            )
        }
        return weatherData
    }
}

