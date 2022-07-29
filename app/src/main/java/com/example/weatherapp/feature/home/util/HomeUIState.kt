package com.example.weatherapp.feature.home.util

import com.example.weatherapp.feature.favouritelocations.model.ShortWeatherInfo

sealed class HomeUIState {
    object Error : HomeUIState()
    object Loading : HomeUIState()
    data class Success(val data: ShortWeatherInfo) : HomeUIState()
}