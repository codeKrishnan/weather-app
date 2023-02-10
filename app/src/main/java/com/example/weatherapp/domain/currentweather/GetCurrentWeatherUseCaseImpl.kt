package com.example.weatherapp.domain.currentweather

import com.example.weatherapp.data.common.Result
import com.example.weatherapp.data.currentweather.model.WeatherAPIResponse
import com.example.weatherapp.data.currentweather.repository.base.CurrentWeatherRepository
import com.example.weatherapp.domain.currentweather.base.GetCurrentWeatherUseCase
import com.example.weatherapp.feature.favouritelocations.util.Coordinates
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetCurrentWeatherUseCaseImpl @Inject constructor(
    private val currentWeatherRepository: CurrentWeatherRepository,
) : GetCurrentWeatherUseCase {

    override suspend fun invoke(
        coordinates: List<Coordinates>,
    ): Result<List<WeatherAPIResponse>> =
        withContext(Dispatchers.Default) {
            val currentWeatherResponses = mutableListOf<WeatherAPIResponse>()

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
                Result.Error(emptyList<WeatherAPIResponse>())
            }
        }
}