package com.example.weatherapp.api.weatherforecast

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Sys(
    @Json(name="pod")
    val partOfTheDay: String
)