package com.example.weatherapp.feature.favouritelocations.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.weatherapp.baseui.widget.ErrorIndicator
import com.example.weatherapp.baseui.widget.LoadingIndicator
import com.example.weatherapp.feature.favouritelocations.FavouriteLocationsViewModel
import com.example.weatherapp.feature.favouritelocations.screen.widget.SearchBox
import com.example.weatherapp.feature.favouritelocations.screen.widget.WeatherQuickPreviewWidget
import com.example.weatherapp.feature.favouritelocations.util.Coordinates

@Composable
fun FavouriteLocationsScreen(
    viewModel: FavouriteLocationsViewModel = hiltViewModel(),
    onWeatherCardClicked: (Coordinates) -> Unit,
    onSearchResultSelected: (Coordinates) -> Unit,
) {
    val uiState = viewModel.favouriteLocationsUIState.collectAsState()

    Surface {
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
                onResultSelected = { locationDetail ->
                    onSearchResultSelected(
                        Coordinates(
                            latitude = locationDetail.latitude,
                            longitude = locationDetail.longitude
                        )
                    )
                },
                onQueryChanged = { query ->
                    viewModel.getRecommendationsForLocationSearch(query = query)
                }
            )
            Spacer(modifier = Modifier.height(18.dp))

            when (val state = uiState.value) {
                FavouriteLocationsViewModel.FavouriteLocationsUIState.Error -> {
                    ErrorIndicator {
                        viewModel.getWeatherInformationOfFavouriteLocations()
                    }
                }
                is FavouriteLocationsViewModel.FavouriteLocationsUIState.Success -> {
                    WeatherQuickPreviewWidget(
                        shortWeatherItems = state.data,
                        onClick = onWeatherCardClicked,
                    )
                }
                FavouriteLocationsViewModel.FavouriteLocationsUIState.Loading -> {
                    LoadingIndicator()
                }
            }
        }
    }
}