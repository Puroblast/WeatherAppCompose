package com.puroblast.weatherappcompose.repository


import com.puroblast.weatherappcompose.network.WeatherApi
import com.puroblast.weatherappcompose.network.response.DetailedData
import com.puroblast.weatherappcompose.network.response.ForeCastData
import com.puroblast.weatherappcompose.utils.extension.runCatchingCancellable
import javax.inject.Inject


class WeatherRepositoryImpl @Inject constructor(
    private val weatherApi: WeatherApi,
) : WeatherRepository {

    override suspend fun collectWeatherData(
        q: String, appId: String, units: String
    ): Result<DetailedData> {
        val detailedDataResult = runCatchingCancellable {
            weatherApi.getWeatherData(
                q, appId, units
            ).getDetailedData()
        }

        return detailedDataResult
    }

    override suspend fun collectForecastData(
        q: String, appId: String, units: String
    ): Result<ForeCastData> {
        val foreCastDataResult = runCatchingCancellable {
            weatherApi.getForeCastData(
                q, appId, units
            )
        }

        return foreCastDataResult
    }


}

