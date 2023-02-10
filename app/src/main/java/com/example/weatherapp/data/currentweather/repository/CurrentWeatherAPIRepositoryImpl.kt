package com.example.weatherapp.data.currentweather.repository


import com.example.weatherapp.data.common.Result
import com.example.weatherapp.data.currentweather.model.WeatherAPIResponse
import com.example.weatherapp.data.currentweather.repository.base.CurrentWeatherRepository
import com.example.weatherapp.data.currentweather.service.CurrentWeatherService
import com.squareup.moshi.JsonDataException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CurrentWeatherAPIRepositoryImpl @Inject constructor(
    private val currentWeatherService: CurrentWeatherService,
) : CurrentWeatherRepository {

    override suspend fun getCurrentWeather(
        latitude: String,
        longitude: String,
    ): Result<WeatherAPIResponse> =
        withContext(Dispatchers.IO) {
            try {
                val response = currentWeatherService.getCurrentWeather(
                    latitude = latitude,
                    longitude = longitude,
                )

                return@withContext if (response.isSuccessful && response.body() != null) {
                    Result.Success(response.body()!!)
                } else {
                    Result.Error("Couldn't fetch current weather: ${response.errorBody()}")
                }

            } catch (exception: Exception) {
                when (exception) {
                    is JsonDataException -> throw exception
                    else -> {
                        Result.Error("Couldn't fetch current weather: ${exception.message ?: ""}")
                    }
                }
            }
        }
}
