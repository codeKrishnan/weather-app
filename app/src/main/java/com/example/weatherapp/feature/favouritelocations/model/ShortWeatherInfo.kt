package com.example.weatherapp.feature.favouritelocations.model

import com.example.weatherapp.R
import com.example.weatherapp.api.currentweather.model.CurrentWeatherResponse

/**
 * Contains a subset of important weather information.
 */
data class ShortWeatherInfo(
    val currentTemperature: Double,
    val countryCode: String,
    val cityName: String,
    val windSpeed: String,
    val humidity: String,
    val weatherType: WeatherType,
)

fun CurrentWeatherResponse.toShortWeatherInfo(): ShortWeatherInfo {

    with(this) {
        return ShortWeatherInfo(
            currentTemperature = main.temp,
            countryCode = sys.country,
            cityName = name,
            windSpeed = "${wind.speed}",
            humidity = "${main.humidity}",
            weatherType = weather.first().main
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

fun WeatherType.getIcon(): Int {
    return when (this) {
        WeatherType.Thunderstorm -> R.drawable.thunderstorm
        WeatherType.Rain -> R.drawable.rainy
        WeatherType.Drizzle -> R.drawable.drizzle
        WeatherType.Snow -> R.drawable.snowflake
        WeatherType.Clear -> R.drawable.sunny
        WeatherType.Clouds -> R.drawable.cloudy
        WeatherType.Unknown -> R.drawable.sunny
    }
}