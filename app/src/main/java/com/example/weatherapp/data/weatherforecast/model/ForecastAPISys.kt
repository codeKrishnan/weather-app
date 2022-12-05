package com.example.weatherapp.data.weatherforecast.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ForecastAPISys(
    @Json(name="pod")
    val partOfTheDay: String
)