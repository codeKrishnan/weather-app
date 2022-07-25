package com.example.weatherapp.api.weatherforecast

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class WeatherForecastResponse(
    val city: City,
    @Json(name = "cnt")
    val timeStampsReturned: Int,
    val list: List<WeatherDetails>,
)