package com.example.weatherapp.feature.favouritelocations

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.weatherapp.usecase.base.GetCurrentWeatherUseCase
import javax.inject.Inject

class FavouriteLocationsViewModelFactory @Inject constructor(
    private val getCurrentWeatherUseCase: GetCurrentWeatherUseCase,
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return FavouriteLocationsViewModel(
            getCurrentWeatherUseCase = getCurrentWeatherUseCase
        ) as T
    }
}