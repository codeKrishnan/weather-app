package com.example.weatherapp.feature.weatherforecast.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.weatherapp.R
import com.example.weatherapp.feature.favouritelocations.screen.widget.ErrorIndicator
import com.example.weatherapp.feature.favouritelocations.screen.widget.LoadingIndicator
import com.example.weatherapp.feature.weatherforecast.WeatherForecastViewModel
import com.example.weatherapp.feature.weatherforecast.screen.widget.DetailedForecastInfoWidget
import com.example.weatherapp.feature.weatherforecast.screen.widget.Header
import com.example.weatherapp.baseui.widget.QuickWeatherInfoBar
import com.example.weatherapp.feature.weatherforecast.util.WeatherForecastUIState


fun Modifier.defaultPadding(): Modifier = padding(horizontal = 24.dp)

@Composable
fun WeatherForecastScreen(
    viewModel: WeatherForecastViewModel,
) {
    val uiState = viewModel.weatherForecastUIState.observeAsState()

    when (val result = uiState.value) {
        is WeatherForecastUIState.Success -> {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(colorResource(id = R.color.grey_background))
                    .padding(
                        vertical = 32.dp,
                    )
            ) {
                Header(
                    shortWeatherInfo = result.data.shortWeatherInfo
                )
                Spacer(modifier = Modifier.height(30.dp))
                QuickWeatherInfoBar(
                    result.data.shortWeatherInfo
                )
                Spacer(modifier = Modifier.height(60.dp))
                DetailedForecastInfoWidget(
                    viewModel.weatherForecastState,
                    result.data.weatherForecastDetails.details
                )
            }
        }
        WeatherForecastUIState.Loading -> {
            LoadingIndicator()
        }
        WeatherForecastUIState.Error, null -> {
            ErrorIndicator {

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun WeatherForecastScreenPreview() {
    WeatherForecastScreen(
        viewModel = viewModel()
    )
}