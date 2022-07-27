package com.example.weatherapp.feature.weatherforecast.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.weatherapp.R
import com.example.weatherapp.feature.base.BaseNavigationViewModel
import com.example.weatherapp.feature.weatherforecast.screen.widget.DetailedForecastInfoWidget
import com.example.weatherapp.feature.weatherforecast.screen.widget.QuickWeatherInfoBar
import com.example.weatherapp.feature.weatherforecast.util.WeatherForecastUIState

@Composable
fun WeatherForecastScreen(
    viewModel: BaseNavigationViewModel,
) {
    val uiState = viewModel.weatherForecastUIState.observeAsState()

    val forecastState = remember {
        viewModel.weatherForecastState
    }

    when (val result = uiState.value) {
        is WeatherForecastUIState.Success -> {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(colorResource(id = R.color.grey_background))
                    .padding(
                        vertical = 32.dp,
                        horizontal = 32.dp,
                    )
            ) {
                Header()
                Spacer(modifier = Modifier.height(30.dp))
                QuickWeatherInfoBar()
                Spacer(modifier = Modifier.height(60.dp))
                DetailedForecastInfoWidget(
                    forecastState,
                    result.data.weatherForecastDetails
                )
            }
        }
        WeatherForecastUIState.Loading -> {

        }
        WeatherForecastUIState.Error, null -> {

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