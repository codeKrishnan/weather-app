package com.example.weatherapp.api.currentweather.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Coord(
    val lat: String,
    val lon: String,
)