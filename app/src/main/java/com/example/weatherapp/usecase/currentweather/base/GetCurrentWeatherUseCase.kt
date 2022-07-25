package com.example.weatherapp.usecase.currentweather.base

import com.example.weatherapp.api.common.Result
import com.example.weatherapp.api.currentweather.model.CurrentWeatherResponse
import com.example.weatherapp.feature.favouritelocations.util.Coordinates

/**
 * Use case to retrieve the current weather of a location.
 */
interface GetCurrentWeatherUseCase {

    suspend operator fun invoke(
        coordinates: List<Coordinates>,
    ): Result<List<CurrentWeatherResponse>>
}