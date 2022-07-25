package com.example.weatherapp.feature.favouritelocations.util

import com.example.weatherapp.feature.favouritelocations.model.ShortWeatherInfo

sealed class FavouriteLocationsUIState {
    object Error : FavouriteLocationsUIState()
    object Loading : FavouriteLocationsUIState()
    data class Success(val data: List<ShortWeatherInfo>) : FavouriteLocationsUIState()
}