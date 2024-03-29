package com.example.weatherapp.data.currentweather.service

import com.example.weatherapp.data.currentweather.model.WeatherAPIResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrentWeatherService {

    @GET("data/2.5/weather")
    suspend fun getCurrentWeather(
        @Query("appid") apiKey: String = "ee4c3053bf9f27c8e39738d9b555f945",
        @Query("lat") latitude: String,
        @Query("lon") longitude: String,
        @Query("units") unit: String = "metric",
    ): Response<WeatherAPIResponse>
}