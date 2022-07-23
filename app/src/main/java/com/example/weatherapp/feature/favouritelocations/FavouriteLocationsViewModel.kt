package com.example.weatherapp.feature.favouritelocations

import androidx.lifecycle.ViewModel
import com.example.weatherapp.usecase.base.GetCurrentWeatherUseCase

class FavouriteLocationsViewModel(
    private val getCurrentWeatherUseCase: GetCurrentWeatherUseCase,
) : ViewModel() {

}