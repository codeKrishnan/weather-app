package com.example.weatherapp.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.data.common.Result
import com.example.weatherapp.domain.currentweather.base.GetCurrentWeatherUseCase
import com.example.weatherapp.feature.favouritelocations.model.ShortWeatherInfo
import com.example.weatherapp.feature.favouritelocations.model.toShortWeatherInfo
import com.example.weatherapp.feature.favouritelocations.util.Coordinates
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyLocationViewModel @Inject constructor(
    private val getCurrentWeatherUseCase: GetCurrentWeatherUseCase,
) : ViewModel() {

    sealed class HomeUIState {
        data class Error(val errorDate: HomePageErrorType) : HomeUIState()
        object Loading : HomeUIState()
        data class Success(val data: ShortWeatherInfo) : HomeUIState()
    }

    enum class HomePageErrorType {
        LOCATION_PERMISSION_DENIED,
        GENERIC_ERROR,
    }

    /**
     * State holder for Home page screen.
     */
    class HomePageState {
        var isLocationAlreadyUpdated = false
        var retrievedCoordinates: Coordinates? = null
    }


    private val _homeUIState = MutableStateFlow<HomeUIState>(HomeUIState.Loading)
    val homeUIState: StateFlow<HomeUIState> = _homeUIState.asStateFlow()

    private val homePageState = HomePageState()

    //region Home page
    fun getWeatherDetailsForHome(
        coordinates: Coordinates,
    ) {
        if (!homePageState.isLocationAlreadyUpdated) {
            _homeUIState.update { HomeUIState.Loading }
        }
        homePageState.retrievedCoordinates = coordinates
        viewModelScope.launch {
            val result = getCurrentWeatherUseCase(
                listOf(coordinates)
            )

            when (result) {
                is Result.Error -> {
                    _homeUIState.update { HomeUIState.Error(HomePageErrorType.GENERIC_ERROR) }
                }
                is Result.Success -> {
                    homePageState.isLocationAlreadyUpdated = true
                    _homeUIState.update {
                        HomeUIState.Success(
                            result.data.first().toShortWeatherInfo()
                        )
                    }
                }
            }
        }
    }

    fun onLocationPermissionDenied() {
        _homeUIState.update { HomeUIState.Error(HomePageErrorType.LOCATION_PERMISSION_DENIED) }
    }

    fun onLocationFetchFailed() {
        _homeUIState.update { HomeUIState.Error(HomePageErrorType.GENERIC_ERROR) }
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