package com.example.weatherapp.feature.weatherforecast.screen.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.weatherapp.R
import com.example.weatherapp.feature.favouritelocations.screen.widget.IconText
import com.example.weatherapp.feature.weatherforecast.model.QuickWeatherInformation

@Composable
fun QuickWeatherInfoBar(quickWeatherInfo: QuickWeatherInformation) {
    with(quickWeatherInfo) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(colorResource(id = R.color.grey_background)),
            horizontalArrangement = Arrangement.SpaceBetween

        ) {
            IconText(iconResource = R.drawable.droplet, text = "$humidity %")
            IconText(iconResource = R.drawable.wind, text = "$windSpeed m/s")
            IconText(iconResource = R.drawable.pressure_gauge, text = "$pressure hPa")
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun QuickWeatherInfoBarPreview() {
    QuickWeatherInfoBar(
        quickWeatherInfo = QuickWeatherInformation(
            weatherDescription = "Cloudy",
            humidity = "60 %",
            windSpeed = "1 m/s",
            pressure = "12 hPa",
            sunriseTime = "7 AM",
            sunsetTime = "6 PM"
        )
    )
}