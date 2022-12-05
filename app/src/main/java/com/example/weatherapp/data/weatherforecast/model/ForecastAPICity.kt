package com.example.weatherapp.data.weatherforecast.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ForecastAPICity(
    val country: String,
    val name: String,
    //unix, UTC
    val sunrise: String,
    val sunset: String,
    val timezone: Int
)