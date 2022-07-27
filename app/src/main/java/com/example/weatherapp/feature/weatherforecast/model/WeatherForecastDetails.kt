package com.example.weatherapp.feature.weatherforecast.model

import com.example.weatherapp.api.weatherforecast.model.WeatherForecastAPIResponse
import com.example.weatherapp.util.toTimeInText
import com.example.weatherapp.util.toWeekDayName
import kotlin.math.roundToInt

data class WeatherForecastDetails(
    val cityName: String,
    val countyName: String,
    val quickWeatherInfo: QuickWeatherInformation,
    val weatherForecastDetails: Map<String, List<WeatherSnippet>>,
)

fun WeatherForecastAPIResponse.toWeatherForecastDetails(): WeatherForecastDetails {

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
            quickWeatherInfo = QuickWeatherInformation(
                weatherDescription = "",
                humidity = "",
                windSpeed = "",
                pressure = "",
                sunriseTime = city.sunrise,
                sunsetTime = city.sunset
            ),
            weatherForecastDetails = weatherForecastDetails
        )
    }
}
