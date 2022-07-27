package com.example.weatherapp.api.weatherforecast.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ForecastAPIWeatherDetails(
    //time of data forecasted in unix, UTC
    val dt: Int,
    //time of data forecasted in ISO, UTC
    val dt_txt: String,
    val main: ForecastAPIMain,
    val sys: ForecastAPISys,
    val weather: List<ForecastAPIWeather>,
    val wind: ForecastAPIWind,
)