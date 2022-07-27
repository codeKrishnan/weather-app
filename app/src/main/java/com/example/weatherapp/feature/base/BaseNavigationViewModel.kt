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
import com.example.weatherapp.feature.weatherforecast.util.WeatherForecastState
import com.example.weatherapp.feature.weatherforecast.util.WeatherForecastUIState
import com.example.weatherapp.usecase.currentweather.base.GetCurrentWeatherUseCase
import com.example.weatherapp.usecase.geocoding.base.GetPlacesForSearchQueryUseCase
import com.example.weatherapp.usecase.weatherforecast.base.GetWeatherForecastForLocationUseCase
import kotlinx.coroutines.launch

class BaseNavigationViewModel(
    private val getCurrentWeatherUseCase: GetCurrentWeatherUseCase,
    private val getPlacesForSearchQueryUseCase: GetPlacesForSearchQueryUseCase,
    private val getWeatherForecastForLocationUseCase: GetWeatherForecastForLocationUseCase,
) : ViewModel() {

    private val _favouriteLocationUIState: MutableLiveData<FavouriteLocationsUIState> =
        MutableLiveData()
    val uiState: LiveData<FavouriteLocationsUIState>
        get() = _favouriteLocationUIState

    private val _weatherForecastUIState: MutableLiveData<WeatherForecastUIState> = MutableLiveData()
    val weatherForecastUIState: LiveData<WeatherForecastUIState>
        get() = _weatherForecastUIState


    val locationSearchState = LocationSearchState()
    val weatherForecastState = WeatherForecastState()

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
        _weatherForecastUIState.value = WeatherForecastUIState.Loading
        viewModelScope.launch {
            val result = getWeatherForecastForLocationUseCase(
                latitude = latitude,
                longitude = longitude
            )

            when (result) {
                is Result.Success -> {
                    _weatherForecastUIState.value =
                        WeatherForecastUIState.Success(result.data.toWeatherForecastDetails())
                }
                is Result.Error -> {
                    _weatherForecastUIState.value = WeatherForecastUIState.Error
                }
            }
        }
    }

    private fun clearSearchRecommendations() {
        locationSearchState.locationDetails.value = emptyList()
    }
}