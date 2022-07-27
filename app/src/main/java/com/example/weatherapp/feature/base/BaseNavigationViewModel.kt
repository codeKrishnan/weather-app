package com.example.weatherapp.feature.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.api.common.Result
import com.example.weatherapp.feature.favouritelocations.model.toShortWeatherInfo
import com.example.weatherapp.feature.favouritelocations.util.Coordinates
import com.example.weatherapp.feature.favouritelocations.util.FavouriteLocations
import com.example.weatherapp.feature.favouritelocations.util.FavouriteLocationsUIState
import com.example.weatherapp.feature.favouritelocations.util.LocationSearchState
import com.example.weatherapp.usecase.currentweather.base.GetCurrentWeatherUseCase
import com.example.weatherapp.usecase.geocoding.base.GetPlacesForSearchQueryUseCase
import kotlinx.coroutines.launch

class BaseNavigationViewModel(
    private val getCurrentWeatherUseCase: GetCurrentWeatherUseCase,
    private val getPlacesForSearchQueryUseCase: GetPlacesForSearchQueryUseCase,
) : ViewModel() {

    private val _favouriteLocationUIState: MutableLiveData<FavouriteLocationsUIState> =
        MutableLiveData()
    val uiState: LiveData<FavouriteLocationsUIState>
        get() = _favouriteLocationUIState

    val locationSearchState = LocationSearchState()

    fun getCurrentWeather(
        coordinates: List<Coordinates> = FavouriteLocations.defaultLocations,
    ) {
        _favouriteLocationUIState.postValue(FavouriteLocationsUIState.Loading)
        clearSearchRecommendations()
        viewModelScope.launch {
            val result = getCurrentWeatherUseCase(
                coordinates
            )

            when (result) {
                is Result.Error -> {
                    _favouriteLocationUIState.postValue(FavouriteLocationsUIState.Error)
                }
                is Result.Success -> {
                    _favouriteLocationUIState.postValue(
                        FavouriteLocationsUIState.Success(
                            result.data.map {
                                it.toShortWeatherInfo()
                            }
                        )
                    )
                }
            }
        }

    }

    fun getRecommendationsForLocationSearch(query: String) {
        viewModelScope.launch {
            val result = getPlacesForSearchQueryUseCase(
                searchQuery = query.ifEmpty { " " }
            )
            if (result is Result.Success) {
                locationSearchState.locationDetails.value = result.data
            }
        }
    }

    private fun clearSearchRecommendations() {
        locationSearchState.locationDetails.value = emptyList()
    }
}