package com.example.weatherapp.data.currentweather.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class WeatherAPIResponse(
    val coord: WeatherAPICoordinates,
    val main: WeatherAPIMain,
    val name: String,
    val sys: WeatherAPISys,
    val weather: List<WeatherAPIWeatherInfo>,
    val wind: WeatherAPI,
)