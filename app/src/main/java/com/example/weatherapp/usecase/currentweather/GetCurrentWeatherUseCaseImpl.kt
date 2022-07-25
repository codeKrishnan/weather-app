package com.example.weatherapp.usecase.currentweather

import com.example.weatherapp.api.common.Result
import com.example.weatherapp.api.currentweather.model.CurrentWeatherResponse
import com.example.weatherapp.api.currentweather.repository.base.CurrentWeatherRepository
import com.example.weatherapp.feature.favouritelocations.util.Coordinates
import com.example.weatherapp.usecase.currentweather.base.GetCurrentWeatherUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetCurrentWeatherUseCaseImpl @Inject constructor(
    private val currentWeatherRepository: CurrentWeatherRepository,
) : GetCurrentWeatherUseCase {

    override suspend fun invoke(
        coordinates: List<Coordinates>,
    ): Result<List<CurrentWeatherResponse>> =
        withContext(Dispatchers.Default) {
            val currentWeatherResponses = mutableListOf<CurrentWeatherResponse>()

            coordinates.forEach { coordinates ->
                val apiResult = currentWeatherRepository.getCurrentWeather(
                    latitude = coordinates.latitude,
                    longitude = coordinates.longitude,
                )
                if (apiResult is Result.Success) {
                    currentWeatherResponses.add(apiResult.data)
                }
            }

            return@withContext if (currentWeatherResponses.isNotEmpty()) {
                Result.Success(currentWeatherResponses)
            } else {
                Result.Error(emptyList<CurrentWeatherResponse>())
            }
        }
}