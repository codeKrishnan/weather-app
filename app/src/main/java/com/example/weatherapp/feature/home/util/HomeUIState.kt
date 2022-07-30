package com.example.weatherapp.feature.home.util

import com.example.weatherapp.feature.favouritelocations.model.ShortWeatherInfo

sealed class HomeUIState {
    data class Error(val errorDate: HomePageErrorType) : HomeUIState()
    object Loading : HomeUIState()
    data class Success(val data: ShortWeatherInfo) : HomeUIState()
}

enum class HomePageErrorType {
    LOCATION_PERMISSION_DENIED,
    GENERIC_ERROR,
}