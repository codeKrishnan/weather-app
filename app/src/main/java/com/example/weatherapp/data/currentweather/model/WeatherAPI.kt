package com.example.weatherapp.data.currentweather.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class WeatherAPI(
    val speed: Double,
)