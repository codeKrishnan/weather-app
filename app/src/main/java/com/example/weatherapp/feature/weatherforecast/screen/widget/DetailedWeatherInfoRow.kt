package com.example.weatherapp.feature.weatherforecast.screen.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.weatherapp.R
import com.example.weatherapp.baseui.theme.WeatherAppTheme
import com.example.weatherapp.baseui.widget.DimText
import com.example.weatherapp.feature.favouritelocations.model.WeatherType
import com.example.weatherapp.feature.weatherforecast.model.WeatherSnippet
import com.example.weatherapp.feature.weatherforecast.util.getMaxAndMinTemp

@Composable
fun DetailedWeatherInfoRow(
    selectedDay: String,
    onClick: (String) -> Unit,
    rowDetails: Map.Entry<String, List<WeatherSnippet>>,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(colorResource(id = R.color.grey_background))
            .padding(
                top = 10.dp,
                bottom = 10.dp,
                start = 24.dp
            )
    ) {

        if (rowDetails.key == selectedDay) {
            DimText(text = rowDetails.key)
            LazyRow {
                items(rowDetails.value.size) {
                    SingleWeatherInfoItem(
                        rowDetails.value[it]
                    )
                }
            }
        } else {
            MaxAndMinTemperatureBar(
                onClick = {
                    onClick(it)
                },
                day = rowDetails.key,
                minAndMaxTemp = rowDetails.value.getMaxAndMinTemp()
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun DetailedWeatherInfoRawPreview() {
    WeatherAppTheme {
        DetailedWeatherInfoRow(
            selectedDay = "",
            onClick = {},
            rowDetails = mapOf(
                "Sunday" to listOf(
                    WeatherSnippet(
                        time = "9 AM",
                        temperature = 21,
                        weatherType = WeatherType.Clear,
                        maxTemperature = 25,
                        minTemperature = 12,
                    )
                ),
            ).entries.toList()[0]
        )
    }
}