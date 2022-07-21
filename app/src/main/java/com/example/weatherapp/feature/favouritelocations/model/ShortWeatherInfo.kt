package com.example.weatherapp.feature.favouritelocations.model

import com.example.weatherapp.api.currentweather.model.CurrentWeatherResponse

/**
 * Contains a subset of important weather information.
 */
data class ShortWeatherInfo(
    val currentTemperature: String,
    val countryCode: String,
    val cityName: String,
    val windSpeed: String,
    val humidity: String,
)

fun CurrentWeatherResponse.toShortWeatherInfo(): ShortWeatherInfo {

    with(this) {
        return ShortWeatherInfo(
            currentTemperature = "${main.temp}",
            countryCode = sys.country,
            cityName = name,
            windSpeed = "${wind.speed}",
            humidity = "${main.humidity}",
        )
    }
}

enum class WeatherType {
    Thunderstorm,
    Rain,
    Drizzle,
    Snow,
    Clear,
    Clouds,
    Unknown,
}