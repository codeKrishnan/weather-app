package com.example.weatherapp.feature.favouritelocations

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.data.common.Result
import com.example.weatherapp.domain.currentweather.base.GetCurrentWeatherUseCase
import com.example.weatherapp.domain.favouriteLocations.GetFavouriteLocationsUseCase
import com.example.weatherapp.domain.geocoding.base.GetPlacesForSearchQueryUseCase
import com.example.weatherapp.domain.userpreferences.IsFavouritesScreenModifiedUseCase
import com.example.weatherapp.feature.favouritelocations.model.ShortWeatherInfo
import com.example.weatherapp.feature.favouritelocations.model.toShortWeatherInfo
import com.example.weatherapp.feature.favouritelocations.util.LocationSearchState
import com.example.weatherapp.feature.favouritelocations.util.SavedLocations
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavouriteLocationsViewModel @Inject constructor(
    private val isFavouritesScreenModifiedUseCase: IsFavouritesScreenModifiedUseCase,
    private val getFavouriteLocationsUseCase: GetFavouriteLocationsUseCase,
    private val getCurrentWeatherUseCase: GetCurrentWeatherUseCase,
    private val getPlacesForSearchQueryUseCase: GetPlacesForSearchQueryUseCase,
) : ViewModel() {

    sealed class FavouriteLocationsUIState {
        object Error : FavouriteLocationsUIState()
        object Loading : FavouriteLocationsUIState()
        data class Success(val data: List<ShortWeatherInfo>) : FavouriteLocationsUIState()
    }

    private val _favouriteLocationsUIState = MutableStateFlow<FavouriteLocationsUIState>(FavouriteLocationsUIState.Loading)
    val favouriteLocationsUIState: StateFlow<FavouriteLocationsUIState> = _favouriteLocationsUIState.asStateFlow()

    val locationSearchState = LocationSearchState()

    init {
        getWeatherInformationOfFavouriteLocations()
    }

    fun getWeatherInformationOfFavouriteLocations() {
        clearSearchRecommendations()
        viewModelScope.launch {
            val favouriteLocations = if (isFavouritesScreenModifiedUseCase()) {
                getFavouriteLocationsUseCase().first()
            } else {
                SavedLocations.defaultLocations
            }
            when (val result = getCurrentWeatherUseCase(coordinates = favouriteLocations)) {
                is Result.Error -> {
                    _favouriteLocationsUIState.update { FavouriteLocationsUIState.Error }
                }
                is Result.Success -> {
                    _favouriteLocationsUIState.update {
                        FavouriteLocationsUIState.Success(
                            result.data.map {
                                it.toShortWeatherInfo()
                            }
                        )
                    }
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