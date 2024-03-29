package com.example.weatherapp.data.geocoding.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GeocodingAPIResponse(
    val country: String,
    val lat: String,
    val lon: String,
    val name: String,
)