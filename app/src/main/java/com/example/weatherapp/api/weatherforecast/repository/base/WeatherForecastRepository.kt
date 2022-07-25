package com.example.weatherapp.api.weatherforecast.repository.base

import com.example.weatherapp.api.common.Result
import com.example.weatherapp.api.weatherforecast.model.WeatherForecastAPIResponse

interface WeatherForecastRepository {

    suspend fun getWeatherForecastForLocation(
        latitude: String,
        longitude: String,
    ): Result<WeatherForecastAPIResponse>
}