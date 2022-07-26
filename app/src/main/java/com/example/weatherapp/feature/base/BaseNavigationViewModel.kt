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
import com.example.weatherapp.feature.weatherforecast.model.toWeatherForecastDetails
import com.example.weatherapp.usecase.currentweather.base.GetCurrentWeatherUseCase
import com.example.weatherapp.usecase.geocoding.base.GetPlacesForSearchQueryUseCase
import com.example.weatherapp.usecase.weatherforecast.base.GetWeatherForecastForLocationUseCase
import kotlinx.coroutines.launch

class BaseNavigationViewModel(
    private val getCurrentWeatherUseCase: GetCurrentWeatherUseCase,
    private val getPlacesForSearchQueryUseCase: GetPlacesForSearchQueryUseCase,
    private val getWeatherForecastForLocationUseCase: GetWeatherForecastForLocationUseCase,
) : ViewModel() {

    private val _uiState: MutableLiveData<FavouriteLocationsUIState> = MutableLiveData()

    val uiState: LiveData<FavouriteLocationsUIState>
        get() = _uiState

    val locationSearchState = LocationSearchState()

    fun getCurrentWeather(
        coordinates: List<Coordinates> = FavouriteLocations.defaultLocations,
    ) {
        _uiState.postValue(FavouriteLocationsUIState.Loading)
        clearSearchRecommendations()
        viewModelScope.launch {
            val result = getCurrentWeatherUseCase(
                coordinates
            )

            when (result) {
                is Result.Error -> {
                    _uiState.postValue(FavouriteLocationsUIState.Error)
                }
                is Result.Success -> {
                    _uiState.postValue(
                        FavouriteLocationsUIState.Success(
                            result.data.map {
                                it.toShortWeatherInfo()
                            }
                        )
                    )
                }
            }
        }
        getWeatherForecastOfLocation(
            coordinates.first().latitude,
            coordinates.first().longitude
        )
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

    fun getWeatherForecastOfLocation(
        latitude: String,
        longitude: String,
    ) {
        viewModelScope.launch {
            val result = getWeatherForecastForLocationUseCase(
                latitude = latitude,
                longitude = longitude
            )

            when (result) {
                is Result.Error -> {
                    println(result.errorData)
                }
                is Result.Success -> {
                    println(result.data)
                    println(result.data.toWeatherForecastDetails())
                }
            }
        }
    }

    private fun clearSearchRecommendations() {
        locationSearchState.locationDetails.value = emptyList()
    }
}