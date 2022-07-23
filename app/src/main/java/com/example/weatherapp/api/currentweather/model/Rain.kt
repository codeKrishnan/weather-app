package com.example.weatherapp.api.currentweather.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Rain(
    @Json(name = "3h")
    val rainForPastThreeHour: Double,
)