package com.example.weatherapp.domain.currentweather.base

import com.example.weatherapp.data.common.Result
import com.example.weatherapp.data.currentweather.model.WeatherAPIResponse
import com.example.weatherapp.feature.favouritelocations.util.Coordinates

/**
 * Use case to retrieve the current weather of a location.
 */
interface GetCurrentWeatherUseCase {

    suspend operator fun invoke(
        coordinates: List<Coordinates>,
    ): Result<List<WeatherAPIResponse>>
}