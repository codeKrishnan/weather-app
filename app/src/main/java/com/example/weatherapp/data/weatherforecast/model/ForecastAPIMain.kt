package com.example.weatherapp.data.weatherforecast.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ForecastAPIMain(
    val feels_like: Double,
    val temp_min: Double?,
    val temp_max: Double?,
    val humidity: String,
    val temp: Double,
)