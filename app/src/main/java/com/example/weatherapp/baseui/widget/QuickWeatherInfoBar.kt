package com.example.weatherapp.baseui.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.weatherapp.R
import com.example.weatherapp.feature.favouritelocations.model.ShortWeatherInfo
import com.example.weatherapp.feature.favouritelocations.model.WeatherType
import com.example.weatherapp.feature.favouritelocations.screen.widget.IconText
import com.example.weatherapp.feature.favouritelocations.util.Coordinates
import com.example.weatherapp.feature.weatherforecast.screen.defaultPadding

@Composable
fun QuickWeatherInfoBar(
    shortWeatherInfo: ShortWeatherInfo,
    horizontalArrangement: Arrangement.Horizontal = Arrangement.SpaceBetween,
) {
    with(shortWeatherInfo) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(colorResource(id = R.color.grey_background))
                .defaultPadding(),
            horizontalArrangement = horizontalArrangement

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
        shortWeatherInfo = ShortWeatherInfo(
            coordinates = Coordinates(
                latitude = "12",
                longitude = "12"
            ),
            currentTemperature = 25.00,
            countryCode = "IND",
            cityName = "Delhi",
            windSpeed = "2",
            humidity = "23",
            weatherType = WeatherType.Clear,
            weatherDescription = "Clear",
            pressure = "100",
        ),
    )
}