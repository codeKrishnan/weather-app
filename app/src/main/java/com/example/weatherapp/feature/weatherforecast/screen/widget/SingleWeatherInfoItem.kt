package com.example.weatherapp.feature.weatherforecast.screen.widget

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherapp.baseui.theme.WeatherAppTheme
import com.example.weatherapp.baseui.widget.BrightText
import com.example.weatherapp.feature.favouritelocations.model.WeatherType
import com.example.weatherapp.feature.favouritelocations.model.getBrightIcons
import com.example.weatherapp.feature.weatherforecast.model.WeatherSnippet

@Composable
fun SingleWeatherInfoItem(weatherSnippet: WeatherSnippet) {
    Column(
        modifier = Modifier
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
        Icon(
            modifier = Modifier.size(40.dp),
            painter = painterResource(
                id = weatherSnippet.weatherType.getBrightIcons()
            ),
            contentDescription = "Weather icons",
            tint = Color.Unspecified
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