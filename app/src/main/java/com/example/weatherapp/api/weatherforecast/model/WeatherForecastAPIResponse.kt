package com.example.weatherapp.api.weatherforecast.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class WeatherForecastAPIResponse(
    val city: City,
    @Json(name = "cnt")
    val timeStampsReturned: Int,
    val list: List<WeatherDetails>,
)