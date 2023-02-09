package com.example.weatherapp.feature.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.data.common.Result
import com.example.weatherapp.feature.favouritelocations.model.toShortWeatherInfo
import com.example.weatherapp.feature.favouritelocations.util.Coordinates
import com.example.weatherapp.feature.favouritelocations.util.FavouriteLocations
import com.example.weatherapp.feature.favouritelocations.util.FavouriteLocationsUIState
import com.example.weatherapp.feature.favouritelocations.util.LocationSearchState
import com.example.weatherapp.feature.home.util.HomePageErrorType
import com.example.weatherapp.feature.home.util.HomePageState
import com.example.weatherapp.feature.home.util.HomeUIState
import com.example.weatherapp.usecase.currentweather.base.GetCurrentWeatherUseCase
import com.example.weatherapp.usecase.geocoding.base.GetPlacesForSearchQueryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BaseNavigationViewModel @Inject constructor(
    private val getCurrentWeatherUseCase: GetCurrentWeatherUseCase,
    private val getPlacesForSearchQueryUseCase: GetPlacesForSearchQueryUseCase,
) : ViewModel() {

    private val _favouriteLocationUIState: MutableLiveData<FavouriteLocationsUIState> =
        MutableLiveData()
    val favouriteLocationsUIState: LiveData<FavouriteLocationsUIState>
        get() = _favouriteLocationUIState

    private val _homeUIState: MutableLiveData<HomeUIState> = MutableLiveData()
    val homeUIState: LiveData<HomeUIState>
        get() = _homeUIState

    val locationSearchState = LocationSearchState()

    private val homePageState = HomePageState()

    //region Favourite Location
    fun getWeatherInformationOfFavouriteLocations(
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
    //endregion Favourite Location

    //region Home page
    fun getWeatherDetailsForHome(
        coordinates: Coordinates,
    ) {
        if (!homePageState.isLocationAlreadyUpdated) {
            _homeUIState.postValue(HomeUIState.Loading)
        }
        homePageState.retrievedCoordinates = coordinates
        viewModelScope.launch {
            val result = getCurrentWeatherUseCase(
                listOf(coordinates)
            )

            when (result) {
                is Result.Error -> {
                    _homeUIState.postValue(HomeUIState.Error(HomePageErrorType.GENERIC_ERROR))
                }
                is Result.Success -> {
                    homePageState.isLocationAlreadyUpdated = true
                    _homeUIState.postValue(
                        HomeUIState.Success(
                            result.data.first().toShortWeatherInfo()
                        )
                    )
                }
            }
        }
    }

    fun onLocationPermissionDenied() {
        _homeUIState.postValue(HomeUIState.Error(HomePageErrorType.LOCATION_PERMISSION_DENIED))
    }

    fun onLocationFetchFailed() {
        _homeUIState.postValue(HomeUIState.Error(HomePageErrorType.GENERIC_ERROR))
    }

    fun onUserClickedRetryOnHomePage() {
        _homeUIState.value = HomeUIState.Loading
        with(homePageState.retrievedCoordinates) {
            if (this == null) {
                _homeUIState.value = HomeUIState.Error(HomePageErrorType.GENERIC_ERROR)
                return
            }
            getWeatherDetailsForHome(
                coordinates = this
            )
        }

    }
    //endregion Home page
}