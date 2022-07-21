package com.example.weatherapp.usecase.base

/**
 * Use case to retrieve the current weather of a location.
 */
interface GetCurrentWeatherUseCase {

    suspend operator fun invoke(
        latitude: String,
        longitude: String,
    )
}