package com.example.weatherapp.api.weatherforecast.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ForecastAPIWind(
    //imperial m/s
    val speed: Double,
)