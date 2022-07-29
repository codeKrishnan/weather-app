package com.example.weatherapp.feature.home.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import com.example.weatherapp.feature.base.BaseNavigationViewModel
import com.example.weatherapp.feature.favouritelocations.screen.widget.ErrorIndicator
import com.example.weatherapp.feature.favouritelocations.screen.widget.LoadingIndicator
import com.example.weatherapp.feature.favouritelocations.util.Coordinates
import com.example.weatherapp.feature.home.screen.widget.HomeScreenContent
import com.example.weatherapp.feature.home.util.HomeUIState

@Composable
fun HomeScreen(
    viewModel: BaseNavigationViewModel,
    onShowWeatherForecastClicked: (Coordinates) -> Unit,
) {
    val uiState = viewModel.homeUIState.observeAsState()

    LaunchedEffect(key1 = "") {
        viewModel.getWeatherDetailsForHome(
            Coordinates(
                "35",
                "139"
            )
        )
    }
    when (val result = uiState.value) {
        HomeUIState.Error -> {
            ErrorIndicator {

            }
        }
        HomeUIState.Loading, null -> {
            LoadingIndicator()
        }
        is HomeUIState.Success -> {
            HomeScreenContent(
                shortWeatherInfo = result.data,
                onShowWeatherForecastClicked = onShowWeatherForecastClicked
            )
        }
    }
}