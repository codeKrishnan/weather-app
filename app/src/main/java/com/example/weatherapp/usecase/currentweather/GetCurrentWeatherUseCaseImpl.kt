package com.example.weatherapp.usecase.currentweather

import com.example.weatherapp.api.common.Result
import com.example.weatherapp.api.currentweather.model.CurrentWeatherResponse
import com.example.weatherapp.api.currentweather.repository.base.CurrentWeatherRepository
import com.example.weatherapp.usecase.currentweather.base.GetCurrentWeatherUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetCurrentWeatherUseCaseImpl @Inject constructor(
    private val currentWeatherRepository: CurrentWeatherRepository,
) : GetCurrentWeatherUseCase {

    override suspend fun invoke(
        latitude: String,
        longitude: String,
    ): Result<CurrentWeatherResponse> =
        withContext(Dispatchers.Default) {
            return@withContext currentWeatherRepository.getCurrentWeather(
                latitude = latitude,
                longitude = longitude,
            )
        }
}