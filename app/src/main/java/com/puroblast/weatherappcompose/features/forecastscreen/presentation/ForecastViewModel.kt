package com.puroblast.weatherappcompose.features.forecastscreen.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.puroblast.weatherappcompose.network.response.ForeCastData
import com.puroblast.weatherappcompose.repository.WeatherRepository
import com.puroblast.weatherappcompose.utils.CITY
import com.puroblast.weatherappcompose.utils.TOKEN
import com.puroblast.weatherappcompose.utils.UNITS
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Locale
import javax.inject.Inject


@HiltViewModel
class ForecastViewModel @Inject constructor(private val weatherRepository: WeatherRepository) :
    ViewModel() {

    private var _foreCastState = MutableStateFlow(ForeCastState())
    val foreCastState = _foreCastState.asStateFlow()

    init {
        collectData()
    }

    private fun collectData() {
        viewModelScope.launch {
            val body = weatherRepository.collectForecastData(CITY, TOKEN, UNITS).getOrElse {
                ForeCastData(
                    listOf()
                )
            }

            val fiveDayForeCast = mutableListOf<DailyForeCast>()
            val dates = mutableListOf<String>()

            body.list.forEach {
                val date = convertDateInString(it.dtTxt)
                if (!dates.contains(date)) {
                    dates.add(date)
                }
            }
            dates.forEach { date ->
                fiveDayForeCast.add(DailyForeCast(body.list.map {
                    SingleForeCast(it.dtTxt, it.main.temp)
                }.filter {
                    convertDateInString(it.date) == date
                }))
            }

            _foreCastState.value = _foreCastState.value.copy(
                fiveDayForeCast = fiveDayForeCast
            )


        }
    }

    private fun convertDateInString(
        dateTime: String
    ): String {
        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        val date = sdf.parse(dateTime)
        val simpleDateFormat = SimpleDateFormat("dd-MM", Locale.getDefault())
        return simpleDateFormat.format(date)
    }
}

