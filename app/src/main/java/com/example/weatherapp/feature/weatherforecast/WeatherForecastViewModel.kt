package com.example.weatherapp.feature.weatherforecast

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.api.common.Result
import com.example.weatherapp.feature.favouritelocations.util.FavouriteLocations
import com.example.weatherapp.feature.weatherforecast.model.toWeatherForecastDetails
import com.example.weatherapp.feature.weatherforecast.util.WeatherForecastState
import com.example.weatherapp.feature.weatherforecast.util.WeatherForecastUIState
import com.example.weatherapp.usecase.weatherforecast.base.GetWeatherForecastForLocationUseCase
import kotlinx.coroutines.launch

class WeatherForecastViewModel(
    private val getWeatherForecastForLocationUseCase: GetWeatherForecastForLocationUseCase,
) : ViewModel() {

    init {
        getWeatherForecastOfLocation(
            FavouriteLocations.defaultLocations.first().latitude,
            FavouriteLocations.defaultLocations.first().longitude
        )
    }

    private val _weatherForecastUIState: MutableLiveData<WeatherForecastUIState> = MutableLiveData()
    val weatherForecastUIState: LiveData<WeatherForecastUIState>
        get() = _weatherForecastUIState

    val weatherForecastState = WeatherForecastState()

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
}