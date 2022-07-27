package com.example.weatherapp.feature.weatherforecast.screen.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.weatherapp.R
import com.example.weatherapp.baseui.theme.WeatherAppTheme
import com.example.weatherapp.feature.favouritelocations.model.WeatherType
import com.example.weatherapp.feature.weatherforecast.model.WeatherSnippet
import com.example.weatherapp.feature.weatherforecast.util.WeatherForecastState

@Composable
fun DetailedForecastInfoWidget(
    forecastState: WeatherForecastState,
    weatherForecastDetails: Map<String, List<WeatherSnippet>>,
) {
    val selectedDay = remember {
        forecastState.selectedDay
    }

    LazyColumn(
        modifier = Modifier
            .background(
                colorResource(id = R.color.grey_background)
            )
    ) {
        items(weatherForecastDetails.size) { index ->
            DetailedWeatherInfoRow(
                selectedDay = selectedDay.value,
                onClick = {
                    selectedDay.value = it
                },
                rowDetails = weatherForecastDetails.entries.toList()[index],
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun DetailedForecastInfoWidgetPreview() {
    WeatherAppTheme {
        DetailedForecastInfoWidget(
            forecastState = WeatherForecastState(),
            weatherForecastDetails = mapOf(
                "Sunday" to listOf(
                    WeatherSnippet(
                        time = "9 AM",
                        temperature = 21,
                        weatherType = WeatherType.Clear,
                        minTemperature = 23,
                        maxTemperature = 34
                    )
                ),
                "Monday" to listOf(
                    WeatherSnippet(
                        time = "9 AM",
                        temperature = 24,
                        weatherType = WeatherType.Clear,
                        minTemperature = 14,
                        maxTemperature = 23
                    )
                )
            )
        )
    }
}

