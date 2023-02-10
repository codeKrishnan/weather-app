package com.example.weatherapp.data.currentweather.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class WeatherAPICoordinates(
    val lon: String,
    val lat: String,
)
