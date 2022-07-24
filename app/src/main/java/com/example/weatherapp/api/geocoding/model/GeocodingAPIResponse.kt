package com.example.weatherapp.api.geocoding.model

data class GeocodingAPIResponse(
    val country: String,
    val lat: Double,
    val lon: Double,
    val name: String,
)