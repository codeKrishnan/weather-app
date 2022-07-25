package com.example.weatherapp.api.weatherforecast

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Wind(
    //imperial m/s
    val speed: Double,
)