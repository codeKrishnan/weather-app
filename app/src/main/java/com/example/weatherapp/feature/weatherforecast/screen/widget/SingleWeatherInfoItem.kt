package com.example.weatherapp.feature.weatherforecast.screen.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherapp.R
import com.example.weatherapp.baseui.theme.WeatherAppTheme
import com.example.weatherapp.baseui.widget.BrightText
import com.example.weatherapp.baseui.widget.GradientIcon
import com.example.weatherapp.feature.favouritelocations.model.WeatherType
import com.example.weatherapp.feature.weatherforecast.model.WeatherSnippet

@Composable
fun SingleWeatherInfoItem(weatherSnippet: WeatherSnippet) {
    Column(
        modifier = Modifier
            .background(
                colorResource(id = R.color.grey_background)
            )
            .padding(
                end = 32.dp,
                top = 12.dp,
                bottom = 12.dp
            ),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        BrightText(text = weatherSnippet.time)
        Spacer(Modifier.height(6.dp))
        GradientIcon(
            modifier = Modifier.size(40.dp),
            weatherType = weatherSnippet.weatherType
        )
        Spacer(Modifier.height(6.dp))
        BrightText(
            text = "${weatherSnippet.temperature}°",
            fontSize = 24.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SingleWeatherInfoItemPreview() {
    WeatherAppTheme {
        SingleWeatherInfoItem(
            weatherSnippet = WeatherSnippet(
                time = "10 AM",
                temperature = 20,
                weatherType = WeatherType.Clear,
                minTemperature = 12,
                maxTemperature = 23,
            )
        )
    }
}