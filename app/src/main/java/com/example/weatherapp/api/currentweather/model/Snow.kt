package com.example.weatherapp.api.currentweather.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Snow(
    @Json(name = "snow.3h")
    val snowForPastThreeHours: Double,
)