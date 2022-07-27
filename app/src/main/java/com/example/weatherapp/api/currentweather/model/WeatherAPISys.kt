package com.example.weatherapp.api.currentweather.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class WeatherAPISys(
    val country: String,
)