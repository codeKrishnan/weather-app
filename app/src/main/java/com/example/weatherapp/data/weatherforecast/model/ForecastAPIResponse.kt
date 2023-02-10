package com.example.weatherapp.data.weatherforecast.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ForecastAPIResponse(
    val city: ForecastAPICity,
    @Json(name = "cnt")
    val timeStampsReturned: Int,
    val list: List<ForecastAPIWeatherDetails>,
)