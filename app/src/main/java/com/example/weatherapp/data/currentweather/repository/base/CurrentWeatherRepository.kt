package com.example.weatherapp.data.currentweather.repository.base

import com.example.weatherapp.data.common.Result
import com.example.weatherapp.data.currentweather.model.WeatherAPIResponse

interface CurrentWeatherRepository {

    /**
     * Gets the current weather of the location.
     *
     * @param latitude Latitude of the location.
     * @param longitude Longitude of the location.
     */
    suspend fun getCurrentWeather(
        latitude: String,
        longitude: String,
    ): Result<WeatherAPIResponse>
}