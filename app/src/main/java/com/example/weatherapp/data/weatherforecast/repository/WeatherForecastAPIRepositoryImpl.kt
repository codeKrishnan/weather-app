package com.example.weatherapp.data.weatherforecast.repository

import com.example.weatherapp.data.common.Result
import com.example.weatherapp.data.weatherforecast.model.ForecastAPIResponse
import com.example.weatherapp.data.weatherforecast.repository.base.WeatherForecastRepository
import com.example.weatherapp.data.weatherforecast.service.WeatherForecastService
import com.squareup.moshi.JsonDataException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class WeatherForecastAPIRepositoryImpl @Inject constructor(
    private val weatherForecastService: WeatherForecastService,
) : WeatherForecastRepository {

    override suspend fun getWeatherForecastForLocation(
        latitude: String,
        longitude: String,
    ): Result<ForecastAPIResponse> =
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
                    is JsonDataException -> throw exception
                    else -> {
                        Result.Error("Couldn't fetch weather forecast: ${exception.message ?: ""}")
                    }
                }
            }
        }
}