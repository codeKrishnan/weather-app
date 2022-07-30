package com.example.weatherapp.feature.home.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import com.example.weatherapp.R
import com.example.weatherapp.feature.base.BaseNavigationViewModel
import com.example.weatherapp.feature.favouritelocations.screen.widget.ErrorIndicator
import com.example.weatherapp.feature.favouritelocations.screen.widget.LoadingIndicator
import com.example.weatherapp.feature.favouritelocations.util.Coordinates
import com.example.weatherapp.feature.home.screen.widget.HomeScreenContent
import com.example.weatherapp.feature.home.util.HomePageErrorType
import com.example.weatherapp.feature.home.util.HomeUIState

@Composable
fun HomeScreen(
    viewModel: BaseNavigationViewModel,
    onShowWeatherForecastClicked: (Coordinates) -> Unit,
    onLocationPermissionUpdateClicked: () -> Unit,
) {
    val uiState = viewModel.homeUIState.observeAsState()

    when (val result = uiState.value) {
        is HomeUIState.Error -> {
            when (result.errorDate) {
                HomePageErrorType.LOCATION_PERMISSION_DENIED -> ErrorIndicator(
                    errorMessage = R.string.location_permission_denied_error_message
                ) {
                    onLocationPermissionUpdateClicked()
                }
                HomePageErrorType.GENERIC_ERROR -> {
                    viewModel.onUserClickedRetryOnHomePage()
                }
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