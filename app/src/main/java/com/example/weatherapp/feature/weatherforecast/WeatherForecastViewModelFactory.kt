package com.example.weatherapp.feature.weatherforecast

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.weatherapp.usecase.weatherforecast.base.GetWeatherForecastForLocationUseCase
import javax.inject.Inject

class WeatherForecastViewModelFactory @Inject constructor(
    private val getWeatherForecastForLocationUseCase: GetWeatherForecastForLocationUseCase,
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return WeatherForecastViewModel(
            getWeatherForecastForLocationUseCase = getWeatherForecastForLocationUseCase
        ) as T
    }
}