package com.example.weatherapp.feature.favouritelocations.widget

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
import com.example.weatherapp.feature.favouritelocations.FavouriteLocationsUIState
import com.example.weatherapp.feature.favouritelocations.FavouriteLocationsViewModel
import com.example.weatherapp.feature.favouritelocations.widget.search.SearchBox

@Composable
fun HomeScreen(
    viewModel: FavouriteLocationsViewModel,
) {
    val uiState = viewModel.uiState.observeAsState()

    Surface(
        color = colorResource(id = R.color.grey_background)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    horizontal = 18.dp,
                    vertical = 32.dp
                ),
        ) {
            SearchBox(
                locationSearchState = viewModel.locationSearchState,
                onLocationClick = { locationDetail ->
                    viewModel.getCurrentWeather(
                        latitude = locationDetail.latitude,
                        longitude = locationDetail.longitude
                    )
                },
                onDoneActionClick = { query ->
                    viewModel.getLocationDetailsForQuery(query = query)
                }
            )
            Spacer(modifier = Modifier.height(24.dp))

            when (val state = uiState.value) {
                FavouriteLocationsUIState.Error -> {
                    ErrorIndicator {
                        viewModel.getCurrentWeather()
                    }
                }
                is FavouriteLocationsUIState.Success -> {
                    WeatherQuickPreviewWidget(
                        state.data
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
private fun HomeScreenPreview() {
    HomeScreen(viewModel())
}