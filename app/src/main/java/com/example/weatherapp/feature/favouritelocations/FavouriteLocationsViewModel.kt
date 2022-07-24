package com.example.weatherapp.feature.favouritelocations

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.api.Result
import com.example.weatherapp.feature.favouritelocations.model.ShortWeatherInfo
import com.example.weatherapp.feature.favouritelocations.model.toShortWeatherInfo
import com.example.weatherapp.usecase.currentweather.base.GetCurrentWeatherUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

sealed class FavouriteLocationsUIState() {
    object Error : FavouriteLocationsUIState()
    object Loading : FavouriteLocationsUIState()
    data class Success(val data: List<ShortWeatherInfo>) : FavouriteLocationsUIState()
}

class FavouriteLocationsViewModel(
    private val getCurrentWeatherUseCase: GetCurrentWeatherUseCase,
) : ViewModel() {

    private val _uiState: MutableLiveData<FavouriteLocationsUIState> = MutableLiveData()

    val uiState: LiveData<FavouriteLocationsUIState>
        get() = _uiState

    fun getCurrentWeather() {
        _uiState.postValue(FavouriteLocationsUIState.Loading)
        viewModelScope.launch {
            delay(3000)
            val result = getCurrentWeatherUseCase(
                latitude = "35",
                longitude = "139",
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
}