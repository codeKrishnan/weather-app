package com.example.weatherapp.api.currentweather.repository


import com.example.weatherapp.api.Result
import com.example.weatherapp.api.currentweather.model.CurrentWeatherResponse
import com.example.weatherapp.api.currentweather.repository.base.CurrentWeatherRepository
import com.example.weatherapp.api.currentweather.service.CurrentWeatherService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.SocketTimeoutException
import javax.inject.Inject

class CurrentWeatherRepositoryImpl @Inject constructor(
    private val currentWeatherService: CurrentWeatherService,
) : CurrentWeatherRepository {

    override suspend fun getCurrentWeather(
        latitude: String,
        longitude: String,
    ): Result<CurrentWeatherResponse> =
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
                    is SocketTimeoutException -> {
                        Result.Error("Couldn't fetch current weather: ${exception.message ?: ""}")
                    }
                    else -> throw exception
                }
            }
        }
}
