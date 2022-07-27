package com.example.weatherapp.feature.weatherforecast.screen.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.weatherapp.R
import com.example.weatherapp.baseui.theme.WeatherAppTheme
import com.example.weatherapp.baseui.widget.BrightText
import com.example.weatherapp.baseui.widget.DimText
import com.example.weatherapp.baseui.widget.GradientIcon
import com.example.weatherapp.feature.favouritelocations.model.WeatherType
import com.example.weatherapp.feature.weatherforecast.model.WeatherSnippet

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
    ) {

        if (rowDetails.key == selectedDay) {
            DimText(text = rowDetails.key)
            LazyRow() {
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
                day = rowDetails.key
            )
        }
    }
}

@Composable
fun MaxAndMinTemperatureBar(
    day: String,
    temperature: Pair<String, String> = Pair("", ""),
    onClick: (String) -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClick(day)
            },
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        BrightText(
            modifier = Modifier
                .width(100.dp),
            text = day
        )
        GradientIcon(
            modifier = Modifier.size(30.dp),
            weatherType = WeatherType.Clear
        )
        Row {
            BrightText(text = temperature.first)
            Spacer(modifier = Modifier.size(8.dp))
            DimText(text = temperature.second)
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
                "" to listOf(
                    WeatherSnippet(
                        time = "",
                        temperature = "",
                        weatherType = WeatherType.Clear
                    )
                )
            ).entries.toList()[0]
        )
    }
}