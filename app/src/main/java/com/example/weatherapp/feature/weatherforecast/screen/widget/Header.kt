package com.example.weatherapp.feature.weatherforecast.screen.widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherapp.R
import com.example.weatherapp.baseui.widget.BrightText
import com.example.weatherapp.feature.favouritelocations.model.ShortWeatherInfo
import com.example.weatherapp.feature.favouritelocations.model.WeatherType
import com.example.weatherapp.feature.favouritelocations.model.getImage
import com.example.weatherapp.feature.favouritelocations.util.Coordinates
import com.example.weatherapp.feature.weatherforecast.screen.defaultPadding
import kotlin.math.roundToInt

@Composable
fun Header(shortWeatherInfo: ShortWeatherInfo) {
    with(shortWeatherInfo) {
        Surface(
            color = colorResource(id = R.color.grey_background)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .defaultPadding(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Column(
                    modifier = Modifier
                        .weight(1f)
                ) {
                    BrightText(
                        text = cityName,
                        fontSize = 20.sp
                    )
                    BrightText(
                        text = "${currentTemperature.roundToInt()}°c",
                        fontSize = 64.sp
                    )
                    BrightText(
                        modifier = Modifier
                            .background(
                                color = colorResource(id = R.color.violet_pill_background),
                                shape = RoundedCornerShape(18.dp)
                            )
                            .padding(
                                horizontal = 12.dp,
                                vertical = 8.dp
                            ),
                        text = shortWeatherInfo.weatherDescription,
                    )
                }
                Image(
                    modifier = Modifier
                        .weight(1f),
                    painter = painterResource(id = weatherType.getImage()),
                    contentDescription = null
                )
            }
        }
    }
}

@Preview
@Composable
private fun HeaderPreview() {
    Header(
        shortWeatherInfo = ShortWeatherInfo(
            coordinates = Coordinates(
                latitude = "12",
                longitude = "24"
            ),
            currentTemperature = 25.00,
            countryCode = "IND",
            cityName = "Delhi",
            windSpeed = "2",
            humidity = "23",
            weatherType = WeatherType.Clear,
            weatherDescription = "Clear",
            pressure = "100"
        )
    )
}
