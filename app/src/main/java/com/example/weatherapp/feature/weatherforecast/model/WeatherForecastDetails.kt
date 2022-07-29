package com.example.weatherapp.feature.weatherforecast.model

import com.example.weatherapp.api.weatherforecast.model.ForecastAPIResponse
import com.example.weatherapp.feature.favouritelocations.model.ShortWeatherInfo
import com.example.weatherapp.util.toTimeInText
import com.example.weatherapp.util.toWeekDayName
import kotlin.math.roundToInt

/**
 * A wrapper class for combining both weather forecast and current weather info.
 * This is useful for the Weather forecast screen as it shows both current and future info.
 */
data class CompleteWeatherInfoWrapper(
    val shortWeatherInfo: ShortWeatherInfo,
    val weatherForecastDetails: WeatherForecastDetails,
)

data class WeatherForecastDetails(
    val cityName: String,
    val countyName: String,
    val sunriseInfo: SunriseInfo,
    val details: Map<String, List<WeatherSnippet>>,
)

fun ForecastAPIResponse.toWeatherForecastDetails(): WeatherForecastDetails {

    with(this) {

        val weatherForecastDetails = mutableMapOf<String, MutableList<WeatherSnippet>>()

        list.forEach {
            val weekDayName = it.dt_txt.toWeekDayName()

            if (weatherForecastDetails.containsKey(weekDayName)) {
                weatherForecastDetails[weekDayName]?.add(
                    WeatherSnippet(
                        time = it.dt_txt.toTimeInText(),
                        temperature = it.main.temp.roundToInt(),
                        weatherType = it.weather.first().main,
                        minTemperature = it.main.temp_min?.roundToInt() ?: 0,
                        maxTemperature = it.main.temp_max?.roundToInt() ?: 0,
                        icon = ""
                    )
                )
            } else {
                weatherForecastDetails[weekDayName] = mutableListOf(
                    WeatherSnippet(
                        time = it.dt_txt.toTimeInText(),
                        temperature = it.main.temp.roundToInt(),
                        weatherType = it.weather.first().main,
                        minTemperature = it.main.temp_min?.roundToInt() ?: 0,
                        maxTemperature = it.main.temp_max?.roundToInt() ?: 0,
                    )
                )
            }
        }

        return WeatherForecastDetails(
            cityName = city.name,
            countyName = city.country,
            sunriseInfo = SunriseInfo(
                sunriseTime = city.sunrise,
                sunsetTime = city.sunset
            ),
            details = weatherForecastDetails
        )
    }
}
