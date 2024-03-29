package com.example.weatherapp.feature.mylocation.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.example.weatherapp.R
import com.example.weatherapp.baseui.widget.ErrorIndicator
import com.example.weatherapp.baseui.widget.LoadingIndicator
import com.example.weatherapp.feature.favouritelocations.util.Coordinates
import com.example.weatherapp.feature.mylocation.MyLocationViewModel
import com.example.weatherapp.feature.mylocation.screen.widget.MyLocationScreenContent

@Composable
fun MyLocationScreen(
    viewModel: MyLocationViewModel,
    onShowWeatherForecastClicked: (Coordinates) -> Unit,
    onLocationPermissionUpdateClicked: () -> Unit,
) {
    val uiState = viewModel.homeUIState.collectAsState()

    when (val result = uiState.value) {
        is MyLocationViewModel.HomeUIState.Error -> {
            when (result.errorDate) {
                MyLocationViewModel.HomePageErrorType.LOCATION_PERMISSION_DENIED -> ErrorIndicator(
                    errorMessage = R.string.location_permission_denied_error_message
                ) {
                    onLocationPermissionUpdateClicked()
                }
                MyLocationViewModel.HomePageErrorType.GENERIC_ERROR -> {
                    viewModel.onUserClickedRetryOnHomePage()
                }
            }
        }
        MyLocationViewModel.HomeUIState.Loading -> {
            LoadingIndicator()
        }
        is MyLocationViewModel.HomeUIState.Success -> {
            MyLocationScreenContent(
                shortWeatherInfo = result.data,
                onShowWeatherForecastClicked = onShowWeatherForecastClicked
            )
        }
    }
}