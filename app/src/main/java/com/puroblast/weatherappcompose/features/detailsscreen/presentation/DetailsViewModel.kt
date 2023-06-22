package com.puroblast.weatherappcompose.features.detailsscreen.presentation

import androidx.lifecycle.ViewModel
import com.puroblast.weatherappcompose.features.weatherscreen.presentation.WeatherState
import com.puroblast.weatherappcompose.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository
): ViewModel() {

    private val _state = MutableStateFlow(WeatherState())
    val state = _state.asStateFlow()


}