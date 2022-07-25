package com.example.weatherapp.api.weatherforecast.model

import com.example.weatherapp.feature.favouritelocations.model.WeatherType
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Weather(
    val description: String,
    val icon: String,
    val main: WeatherType,
)