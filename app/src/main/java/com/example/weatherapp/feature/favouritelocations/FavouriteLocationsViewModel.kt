package com.example.weatherapp.feature.favouritelocations

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.api.currentweather.Result
import com.example.weatherapp.usecase.base.GetCurrentWeatherUseCase
import kotlinx.coroutines.launch

class FavouriteLocationsViewModel(
    private val getCurrentWeatherUseCase: GetCurrentWeatherUseCase,
) : ViewModel() {

    fun getCurrentWeather() {
        viewModelScope.launch {
            val result = getCurrentWeatherUseCase(
                latitude = "35",
                longitude = "139",
            )

            when(result){
                is Result.Error -> {
                    println("Failure")
                    println(result.errorData)
                }
                is Result.Success -> {
                    println("Success")
                    println(result.data)
                }
            }
        }
    }
}