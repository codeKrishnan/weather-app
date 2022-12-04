package com.example.weatherapp.feature.favouritelocations.model

import com.example.weatherapp.R
import com.example.weatherapp.data.currentweather.model.WeatherAPIResponse
import com.example.weatherapp.feature.favouritelocations.util.Coordinates
import com.example.weatherapp.util.isAfter
import com.example.weatherapp.util.toHoursAndMinutes

/**
 * Contains a subset of important weather information.
 */
data class ShortWeatherInfo(
    val coordinates: Coordinates,
    val currentTemperature: Double,
    val feelsLikeTemperature: String = "",
    val countryCode: String,
    val cityName: String,
    val windSpeed: String,
    val humidity: String,
    val weatherType: WeatherType,
    val weatherDescription: String,
    val pressure: String,
    val isSunrise: Boolean = true,
    val sunsetOrRaiseTime: String = "",
)

fun WeatherAPIResponse.toShortWeatherInfo(): ShortWeatherInfo {

    with(this) {

        var isSunrise = false
        val sunsetOrRaiseTime = if (!sys.sunrise.isAfter()) {
            isSunrise = true
            sys.sunrise.toHoursAndMinutes()
        } else {
            sys.sunset.toHoursAndMinutes()
        }
        return ShortWeatherInfo(
            coordinates = Coordinates(
                latitude = coord.lat,
                longitude = coord.lon
            ),
            currentTemperature = main.temp,
            countryCode = sys.country ?: "",
            cityName = name,
            windSpeed = "${wind.speed}",
            humidity = "${main.humidity}",
            weatherType = weather.first().main,
            weatherDescription = weather.first().description,
            pressure = main.pressure,
            feelsLikeTemperature = "${main.feels_like.toInt()}",
            sunsetOrRaiseTime = sunsetOrRaiseTime,
            isSunrise = isSunrise
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

fun WeatherType.getImage(): Int {
    return when (this) {
        WeatherType.Thunderstorm -> R.drawable.thunderstorm_colour
        WeatherType.Rain -> R.drawable.rainy_colour
        WeatherType.Drizzle -> R.drawable.drizzle_colour
        WeatherType.Snow -> R.drawable.snow_colour
        WeatherType.Clear -> R.drawable.sunny_colour
        WeatherType.Clouds -> R.drawable.clouds
        WeatherType.Unknown -> R.drawable.sunny_colour
    }
}

fun WeatherType.getBrightIcons(): Int {
    return when (this) {
        WeatherType.Thunderstorm -> R.drawable.stormy_bright
        WeatherType.Rain -> R.drawable.rainy_bright
        WeatherType.Drizzle -> R.drawable.drizzle_bright
        WeatherType.Snow -> R.drawable.snowy_bright
        WeatherType.Clear -> R.drawable.clear_bright
        WeatherType.Clouds -> R.drawable.cloudy_bright
        WeatherType.Unknown -> R.drawable.cloudy_bright
    }
}
