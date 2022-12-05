package com.example.weatherapp.data.currentweather.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class WeatherAPIMain(
    val feels_like: Double,
    val humidity: Int,
    val temp: Double,
    val pressure: String,
)