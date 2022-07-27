package com.example.weatherapp.feature.favouritelocations.model

import com.example.weatherapp.R
import com.example.weatherapp.api.currentweather.model.WeatherAPIResponse
import com.example.weatherapp.feature.favouritelocations.util.Coordinates

/**
 * Contains a subset of important weather information.
 */
data class ShortWeatherInfo(
    val coordinates: Coordinates,
    val currentTemperature: Double,
    val countryCode: String,
    val cityName: String,
    val windSpeed: String,
    val humidity: String,
    val weatherType: WeatherType,
)

fun WeatherAPIResponse.toShortWeatherInfo(): ShortWeatherInfo {

    with(this) {
        return ShortWeatherInfo(
            coordinates = Coordinates(
                latitude = coord.lat,
                longitude = coord.lon
            ),
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