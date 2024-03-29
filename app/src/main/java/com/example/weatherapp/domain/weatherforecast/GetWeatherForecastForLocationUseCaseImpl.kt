package com.example.weatherapp.domain.weatherforecast

import com.example.weatherapp.data.common.Result
import com.example.weatherapp.data.weatherforecast.model.ForecastAPIResponse
import com.example.weatherapp.data.weatherforecast.repository.base.WeatherForecastRepository
import com.example.weatherapp.domain.weatherforecast.base.GetWeatherForecastForLocationUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetWeatherForecastForLocationUseCaseImpl @Inject constructor(
    private val weatherForecastRepository: WeatherForecastRepository,
) : GetWeatherForecastForLocationUseCase {

    override suspend fun invoke(
        latitude: String,
        longitude: String,
    ): Result<ForecastAPIResponse> = withContext(Dispatchers.Default) {
        return@withContext weatherForecastRepository.getWeatherForecastForLocation(
            latitude = latitude,
            longitude = longitude,
        )
    }
}