package com.example.weatherapp.data.currentweather.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class WeatherAPISys(
    val country: String?,
    val sunrise: Long,
    val sunset: Long,
)