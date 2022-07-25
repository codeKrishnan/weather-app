package com.example.weatherapp.usecase.weatherforecast

import com.example.weatherapp.api.common.Result
import com.example.weatherapp.api.weatherforecast.model.WeatherForecastAPIResponse
import com.example.weatherapp.api.weatherforecast.repository.base.WeatherForecastRepository
import com.example.weatherapp.usecase.weatherforecast.base.GetWeatherForecastForLocationUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetWeatherForecastForLocationUseCaseImpl @Inject constructor(
    private val weatherForecastRepository: WeatherForecastRepository,
) : GetWeatherForecastForLocationUseCase {

    override suspend fun invoke(
        latitude: String,
        longitude: String,
    ): Result<WeatherForecastAPIResponse> = withContext(Dispatchers.Default) {
        return@withContext weatherForecastRepository.getWeatherForecastForLocation(
            latitude = latitude,
            longitude = longitude,
        )
    }
}