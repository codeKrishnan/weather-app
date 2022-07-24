package com.example.weatherapp.feature.favouritelocations

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.weatherapp.usecase.currentweather.base.GetCurrentWeatherUseCase
import com.example.weatherapp.usecase.geocoding.base.GetPlacesForSearchQueryUseCase
import javax.inject.Inject

class FavouriteLocationsViewModelFactory @Inject constructor(
    private val getCurrentWeatherUseCase: GetCurrentWeatherUseCase,
    private val getPlacesForSearchQueryUseCase: GetPlacesForSearchQueryUseCase,
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return FavouriteLocationsViewModel(
            getCurrentWeatherUseCase = getCurrentWeatherUseCase,
            getPlacesForSearchQueryUseCase = getPlacesForSearchQueryUseCase,
        ) as T
    }
}