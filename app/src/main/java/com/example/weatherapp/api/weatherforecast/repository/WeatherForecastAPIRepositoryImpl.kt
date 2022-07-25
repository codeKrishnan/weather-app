package com.example.weatherapp.api.weatherforecast.repository

import com.example.weatherapp.api.common.Result
import com.example.weatherapp.api.weatherforecast.model.WeatherForecastAPIResponse
import com.example.weatherapp.api.weatherforecast.repository.base.WeatherForecastRepository
import com.example.weatherapp.api.weatherforecast.service.WeatherForecastService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.SocketTimeoutException
import javax.inject.Inject

class WeatherForecastAPIRepositoryImpl @Inject constructor(
    private val weatherForecastService: WeatherForecastService,
) : WeatherForecastRepository {

    override suspend fun getWeatherForecastForLocation(
        latitude: String,
        longitude: String,
    ): Result<WeatherForecastAPIResponse> =
        withContext(Dispatchers.IO) {
            try {
                val response = weatherForecastService.getWeatherForecast(
                    latitude = latitude,
                    longitude = longitude,
                )

                return@withContext if (response.isSuccessful && response.body() != null) {
                    Result.Success(response.body()!!)
                } else {
                    Result.Error("Couldn't fetch weather forecast: ${response.errorBody()}")
                }

            } catch (exception: Exception) {
                when (exception) {
                    is SocketTimeoutException -> {
                        Result.Error("Couldn't fetch weather forecast: ${exception.message ?: ""}")
                    }
                    else -> throw exception
                }
            }
        }
}