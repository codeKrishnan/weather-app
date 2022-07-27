package com.example.weatherapp.api.currentweather.repository.base

import com.example.weatherapp.api.common.Result
import com.example.weatherapp.api.currentweather.model.WeatherAPIResponse

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