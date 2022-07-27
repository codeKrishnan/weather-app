package com.example.weatherapp.feature.weatherforecast.screen.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.weatherapp.R
import com.example.weatherapp.baseui.theme.WeatherAppTheme

@Composable
fun DetailedForecastInfoWidget() {
    LazyColumn(
        modifier = Modifier
            .background(
                colorResource(id = R.color.grey_background)
            )
    ) {
        items(4) {
            DetailedWeatherInfoRow()
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun DetailedForecastInfoWidgetPreview() {
    WeatherAppTheme {
        DetailedForecastInfoWidget()
    }
}

