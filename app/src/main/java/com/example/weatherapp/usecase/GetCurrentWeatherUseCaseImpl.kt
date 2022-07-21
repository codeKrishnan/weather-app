package com.example.weatherapp.usecase

import com.example.weatherapp.api.currentweather.repository.base.CurrentWeatherRepository
import com.example.weatherapp.usecase.base.GetCurrentWeatherUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetCurrentWeatherUseCaseImpl @Inject constructor(
    private val currentWeatherRepository: CurrentWeatherRepository,
) : GetCurrentWeatherUseCase {

    override suspend fun invoke(
        latitude: String,
        longitude: String,
    ) {
        withContext(Dispatchers.Default) {
            val result = currentWeatherRepository.getCurrentWeather(
                latitude = latitude,
                longitude = longitude,
            )
        }
    }
}