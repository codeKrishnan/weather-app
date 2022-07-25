package com.example.weatherapp.usecase.currentweather.base

import com.example.weatherapp.api.common.Result
import com.example.weatherapp.api.currentweather.model.CurrentWeatherResponse

/**
 * Use case to retrieve the current weather of a location.
 */
interface GetCurrentWeatherUseCase {

    suspend operator fun invoke(
        latitude: String,
        longitude: String,
    ): Result<CurrentWeatherResponse>
}