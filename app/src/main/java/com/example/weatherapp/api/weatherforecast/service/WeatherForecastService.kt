package com.example.weatherapp.api.weatherforecast.service

import com.example.weatherapp.api.weatherforecast.model.WeatherForecastAPIResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherForecastService {

    @GET("/data/2.5/forecast")
    fun getWeatherForecast(
        @Query("appid") apiKey: String = "ee4c3053bf9f27c8e39738d9b555f945",
        @Query("lat") latitude: String,
        @Query("lon") longitude: String,
        @Query("units") units: String = "metric",
    ): Response<WeatherForecastAPIResponse>
}