package com.example.weatherapp.feature.weatherforecast.model

import com.example.weatherapp.feature.favouritelocations.model.WeatherType

data class WeatherSnippet(
    val time: String,
    val temperature: Int,
    val minTemperature: Int,
    val maxTemperature: Int,
    val weatherType: WeatherType,
)