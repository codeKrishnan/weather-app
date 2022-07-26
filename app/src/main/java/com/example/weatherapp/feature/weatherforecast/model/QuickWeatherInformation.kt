package com.example.weatherapp.feature.weatherforecast.model

data class QuickWeatherInformation(
    val weatherDescription: String,
    val humidity: String,
    val windSpeed: String,
    val pressure: String,
    val sunriseTime: String,
    val sunsetTime: String,
)