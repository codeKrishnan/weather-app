package com.example.weatherapp.api.currentweather.repository


import com.example.weatherapp.api.currentweather.Result
import com.example.weatherapp.api.currentweather.model.CurrentWeatherResponse
import com.example.weatherapp.api.currentweather.repository.base.CurrentWeatherRepository
import com.example.weatherapp.api.currentweather.service.CurrentWeatherService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CurrentWeatherRepositoryImpl(
    private val currentWeatherService: CurrentWeatherService,
) : CurrentWeatherRepository {

    override suspend fun getCurrentWeather(
        latitude: String,
        longitude: String,
    ): Result<CurrentWeatherResponse> =
        withContext(Dispatchers.Default) {
            try {
                val response = currentWeatherService.getCurrentWeather(
                    latitude = "35",
                    longitude = "139",
                )

                return@withContext if (response.isSuccessful && response.body() != null) {
                    Result.Success(response.body()!!)
                } else {
                    Result.Error("API request was unsuccessful")
                }

            } catch (exception: Exception) {
                return@withContext Result.Error("Something went wrong")
            }
        }
}
