package com.example.weatherapp.data.weatherforecast.repository.base

import com.example.weatherapp.data.common.Result
import com.example.weatherapp.data.weatherforecast.model.ForecastAPIResponse

interface WeatherForecastRepository {

    suspend fun getWeatherForecastForLocation(
        latitude: String,
        longitude: String,
    ): Result<ForecastAPIResponse>
}