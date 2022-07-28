package com.example.weatherapp.feature.favouritelocations.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.weatherapp.R
import com.example.weatherapp.feature.base.BaseNavigationViewModel
import com.example.weatherapp.feature.favouritelocations.screen.widget.ErrorIndicator
import com.example.weatherapp.feature.favouritelocations.screen.widget.LoadingIndicator
import com.example.weatherapp.feature.favouritelocations.screen.widget.SearchBox
import com.example.weatherapp.feature.favouritelocations.screen.widget.WeatherQuickPreviewWidget
import com.example.weatherapp.feature.favouritelocations.util.Coordinates
import com.example.weatherapp.feature.favouritelocations.util.FavouriteLocationsUIState

@Composable
fun FavouriteLocationsScreen(
    viewModel: BaseNavigationViewModel,
    onWeatherCardClicked: (Coordinates) -> Unit,
) {
    val uiState = viewModel.uiState.observeAsState()

    Surface(
        color = colorResource(id = R.color.grey_background)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    start = 18.dp,
                    end = 18.dp,
                    top = 12.dp,
                ),
        ) {
            SearchBox(
                locationSearchState = viewModel.locationSearchState,
                onLocationClick = { locationDetail ->
                    viewModel.getCurrentWeather(
                        listOf(
                            Coordinates(
                                latitude = locationDetail.latitude,
                                longitude = locationDetail.longitude
                            )
                        )
                    )
                },
                onQueryChanged = { query ->
                    viewModel.getRecommendationsForLocationSearch(query = query)
                }
            )
            Spacer(modifier = Modifier.height(18.dp))

            when (val state = uiState.value) {
                FavouriteLocationsUIState.Error -> {
                    ErrorIndicator {
                        viewModel.getCurrentWeather()
                    }
                }
                is FavouriteLocationsUIState.Success -> {
                    WeatherQuickPreviewWidget(
                        shortWeatherItems = state.data,
                        onClick = onWeatherCardClicked,
                    )
                }
                FavouriteLocationsUIState.Loading, null -> {
                    LoadingIndicator()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun FavouriteLocationsPreview() {
    FavouriteLocationsScreen(
        viewModel = viewModel(),
        onWeatherCardClicked = {}
    )
}