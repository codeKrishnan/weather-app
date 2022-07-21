package com.example.weatherapp.api.currentweather.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Clouds(
    @Json(name = "clouds.all")
    val cloudPercentage: Double,
)