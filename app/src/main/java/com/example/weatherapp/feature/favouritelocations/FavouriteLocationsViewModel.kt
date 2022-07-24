package com.example.weatherapp.feature.favouritelocations

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.api.Result
import com.example.weatherapp.feature.favouritelocations.model.ShortWeatherInfo
import com.example.weatherapp.feature.favouritelocations.model.toShortWeatherInfo
import com.example.weatherapp.feature.favouritelocations.util.LocationSearchState
import com.example.weatherapp.usecase.currentweather.base.GetCurrentWeatherUseCase
import com.example.weatherapp.usecase.geocoding.base.GetPlacesForSearchQueryUseCase
import kotlinx.coroutines.launch

sealed class FavouriteLocationsUIState() {
    object Error : FavouriteLocationsUIState()
    object Loading : FavouriteLocationsUIState()
    data class Success(val data: List<ShortWeatherInfo>) : FavouriteLocationsUIState()
}

class FavouriteLocationsViewModel(
    private val getCurrentWeatherUseCase: GetCurrentWeatherUseCase,
    private val getPlacesForSearchQueryUseCase: GetPlacesForSearchQueryUseCase,
) : ViewModel() {

    private val _uiState: MutableLiveData<FavouriteLocationsUIState> = MutableLiveData()

    val uiState: LiveData<FavouriteLocationsUIState>
        get() = _uiState

    val locationSearchState = LocationSearchState()

    fun getCurrentWeather(
        latitude: String = "35",
        longitude: String = "139",
    ) {
        _uiState.postValue(FavouriteLocationsUIState.Loading)
        viewModelScope.launch {
            val result = getCurrentWeatherUseCase(
                latitude = latitude,
                longitude = longitude,
            )

            when (result) {
                is Result.Error -> {
                    _uiState.postValue(FavouriteLocationsUIState.Error)
                }
                is Result.Success -> {
                    _uiState.postValue(
                        FavouriteLocationsUIState.Success(
                            listOf(
                                result.data.toShortWeatherInfo()
                            )
                        )
                    )
                }
            }
        }
    }

    fun getRecommendationsForLocationSearch(query: String) {
        viewModelScope.launch {
            val result = getPlacesForSearchQueryUseCase(query)
            if (result is Result.Success) {
                locationSearchState.locationDetails.value = result.data
            }
        }
    }
}