package com.puroblast.weatherappcompose.weatherscreen.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.puroblast.weatherappcompose.network.model.RandomState
import com.puroblast.weatherappcompose.repository.WeatherRepositoryImpl
import com.puroblast.weatherappcompose.utils.CITY
import com.puroblast.weatherappcompose.utils.TAG
import com.puroblast.weatherappcompose.utils.TOKEN
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch



class WeatherViewModel: ViewModel() {

    private val weatherRepository = WeatherRepositoryImpl()
    private val _state = MutableStateFlow(RandomState())
    val state = _state.asStateFlow()

    fun collectData() {
        viewModelScope.launch {
            
            val result = weatherRepository.collectWeatherData(CITY, TOKEN)
            if(result.isSuccess) {
                val body = result.getOrNull()
                if (body != null) {
                    _state.value = _state.value.copy(text = body.main.temp.toString())
                } else {
                    Log.d(TAG, "collectData: Body is Null")
                }
            } else {
                Log.d(TAG, "collectData: Failure")
            }
        }
    }
}