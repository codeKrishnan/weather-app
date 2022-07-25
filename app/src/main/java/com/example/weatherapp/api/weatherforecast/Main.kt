package com.example.weatherapp.api.weatherforecast

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Main(
    val feels_like: Double,
    val humidity: Int,
    val temp: Double,
)