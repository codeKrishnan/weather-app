package com.example.weatherapp.feature.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.weatherapp.usecase.currentweather.base.GetCurrentWeatherUseCase
import com.example.weatherapp.usecase.geocoding.base.GetPlacesForSearchQueryUseCase
import com.example.weatherapp.usecase.weatherforecast.base.GetWeatherForecastForLocationUseCase
import javax.inject.Inject

class BaseNavigationViewModelFactory @Inject constructor(
    private val getCurrentWeatherUseCase: GetCurrentWeatherUseCase,
    private val getPlacesForSearchQueryUseCase: GetPlacesForSearchQueryUseCase,
    private val getWeatherForecastForLocationUseCase: GetWeatherForecastForLocationUseCase,
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return BaseNavigationViewModel(
            getCurrentWeatherUseCase = getCurrentWeatherUseCase,
            getPlacesForSearchQueryUseCase = getPlacesForSearchQueryUseCase,
            getWeatherForecastForLocationUseCase = getWeatherForecastForLocationUseCase
        ) as T
    }
}