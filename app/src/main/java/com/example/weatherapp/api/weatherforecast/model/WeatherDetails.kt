package com.example.weatherapp.api.weatherforecast.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class WeatherDetails(
    //time of data forecasted in unix, UTC
    val dt: Int,
    //time of data forecasted in ISO, UTC
    val dt_txt: String,
    val main: Main,
    val sys: Sys,
    val weather: List<Weather>,
    val wind: Wind,
)