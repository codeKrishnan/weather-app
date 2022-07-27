package com.example.weatherapp.feature.weatherforecast.util

import com.example.weatherapp.feature.weatherforecast.model.WeatherForecastDetails

sealed class WeatherForecastUIState {
    object Error : WeatherForecastUIState()
    object Loading : WeatherForecastUIState()
    data class Success(val data: WeatherForecastDetails) : WeatherForecastUIState()
}