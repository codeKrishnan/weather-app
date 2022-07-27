package com.example.weatherapp.feature.weatherforecast

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.api.common.Result
import com.example.weatherapp.feature.favouritelocations.model.toShortWeatherInfo
import com.example.weatherapp.feature.favouritelocations.util.Coordinates
import com.example.weatherapp.feature.weatherforecast.model.CompleteWeatherInfoWrapper
import com.example.weatherapp.feature.weatherforecast.model.toWeatherForecastDetails
import com.example.weatherapp.feature.weatherforecast.util.WeatherForecastState
import com.example.weatherapp.feature.weatherforecast.util.WeatherForecastUIState
import com.example.weatherapp.usecase.currentweather.base.GetCurrentWeatherUseCase
import com.example.weatherapp.usecase.weatherforecast.base.GetWeatherForecastForLocationUseCase
import kotlinx.coroutines.launch

class WeatherForecastViewModel(
    private val getWeatherForecastForLocationUseCase: GetWeatherForecastForLocationUseCase,
    private val getCurrentWeatherUseCase: GetCurrentWeatherUseCase,
) : ViewModel() {

    private val _weatherForecastUIState: MutableLiveData<WeatherForecastUIState> = MutableLiveData()
    val weatherForecastUIState: LiveData<WeatherForecastUIState>
        get() = _weatherForecastUIState

    val weatherForecastState = WeatherForecastState()

    fun getWeatherForecastOfLocation(
        coordinates: Coordinates,
    ) {
        _weatherForecastUIState.value = WeatherForecastUIState.Loading
        viewModelScope.launch {

            val currentWeatherResult = getCurrentWeatherUseCase(
                coordinates = listOf(coordinates)
            )

            if (currentWeatherResult is Result.Success) {

                val weatherForecastResult = getWeatherForecastForLocationUseCase(
                    latitude = coordinates.latitude,
                    longitude = coordinates.longitude,
                )

                when (weatherForecastResult) {
                    is Result.Success -> {
                        _weatherForecastUIState.value =
                            WeatherForecastUIState.Success(
                                CompleteWeatherInfoWrapper(
                                    currentWeatherResult.data.first().toShortWeatherInfo(),
                                    weatherForecastResult.data.toWeatherForecastDetails()
                                )
                            )
                    }
                    is Result.Error -> {
                        _weatherForecastUIState.value = WeatherForecastUIState.Error
                    }
                }
            }
        }
    }
}