package com.example.weatherapp.usecase.weatherforecast.base

import com.example.weatherapp.api.common.Result
import com.example.weatherapp.api.weatherforecast.model.WeatherForecastAPIResponse

interface GetWeatherForecastForLocationUseCase {

    suspend operator fun invoke(
        latitude: String,
        longitude: String,
    ): Result<WeatherForecastAPIResponse>
}