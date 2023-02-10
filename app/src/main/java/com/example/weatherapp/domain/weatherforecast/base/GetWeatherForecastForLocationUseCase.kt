package com.example.weatherapp.domain.weatherforecast.base

import com.example.weatherapp.data.common.Result
import com.example.weatherapp.data.weatherforecast.model.ForecastAPIResponse

interface GetWeatherForecastForLocationUseCase {

    suspend operator fun invoke(
        latitude: String,
        longitude: String,
    ): Result<ForecastAPIResponse>
}