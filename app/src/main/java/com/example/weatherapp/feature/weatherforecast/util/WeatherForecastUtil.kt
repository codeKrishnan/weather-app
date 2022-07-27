package com.example.weatherapp.feature.weatherforecast.util

import com.example.weatherapp.feature.weatherforecast.model.WeatherSnippet


fun List<WeatherSnippet>.getMaxAndMinTemp(): Pair<String, String> {

    val minTemp = this.minOfOrNull { it.minTemperature } ?: 0
    val maxTemp = this.maxOfOrNull { it.maxTemperature } ?: 0

    return Pair("$maxTemp", "$minTemp")
}