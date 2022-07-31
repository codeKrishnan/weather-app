package com.example.weatherapp.feature.favouritelocations.screen.widget

import androidx.annotation.DrawableRes
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.weatherapp.R
import com.example.weatherapp.baseui.theme.BlueTint
import com.example.weatherapp.baseui.theme.GreyCardBackground
import com.example.weatherapp.baseui.widget.BrightText
import com.example.weatherapp.baseui.widget.BrightTextLarge
import com.example.weatherapp.baseui.widget.DimText
import com.example.weatherapp.baseui.widget.GradientIcon
import com.example.weatherapp.feature.favouritelocations.model.ShortWeatherInfo
import com.example.weatherapp.feature.favouritelocations.model.WeatherType
import com.example.weatherapp.feature.favouritelocations.util.Coordinates
import kotlin.math.roundToInt


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun WeatherQuickPreviewWidget(
    shortWeatherItems: List<ShortWeatherInfo>,
    onClick: (Coordinates) -> Unit,
) {
    LazyVerticalGrid(
        cells = GridCells.Adaptive(minSize = 150.dp),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        items(shortWeatherItems.size) {
            WeatherQuickPreviewCard(
                shortWeatherItems[it],
                onClick
            )
        }
    }
}

@Composable
private fun WeatherQuickPreviewCard(
    shortWeatherInfo: ShortWeatherInfo,
    onClick: (Coordinates) -> Unit,
) {
    Surface(
        modifier = Modifier
            .padding(8.dp)
            .clickable {
                onClick(shortWeatherInfo.coordinates)
            }
            .wrapContentSize(),
        color = GreyCardBackground,
        shape = RoundedCornerShape(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            GradientIcon(
                modifier = Modifier
                    .padding(16.dp)
                    .size(50.dp),
                weatherType = shortWeatherInfo.weatherType
            )
        }
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Column(
                    modifier = Modifier
                        .padding(end = 16.dp)
                ) {
                    BrightTextLarge(
                        modifier = Modifier
                            .padding(bottom = 8.dp),
                        text = "${shortWeatherInfo.currentTemperature.roundToInt()}\u00B0",
                    )
                    BrightText(
                        text = shortWeatherInfo.cityName,
                    )
                    DimText(
                        text = shortWeatherInfo.countryCode,
                    )
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconText(
                    iconResource = R.drawable.wind,
                    text = shortWeatherInfo.windSpeed
                )
                IconText(
                    iconResource = R.drawable.droplet,
                    text = shortWeatherInfo.humidity
                )
            }
        }
    }
}

@Composable
fun IconText(
    @DrawableRes iconResource: Int,
    text: String,
    isBrightText: Boolean = true,
) {
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier
                .size(32.dp)
                .padding(end = 8.dp),
            painter = painterResource(id = iconResource),
            contentDescription = "Weather icon",
            tint = BlueTint
        )
        if (isBrightText) {
            BrightText(text = text)
        } else {
            DimText(text = text)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun WeatherQuickPreviewPreview() {
    WeatherQuickPreviewCard(
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
        ),
        onClick = {}
    )
}

@Preview
@Composable
fun WeatherQuickPreviewWidgetPreview() {
    WeatherQuickPreviewWidget(
        shortWeatherItems = listOf(
            ShortWeatherInfo(
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
            ShortWeatherInfo(
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
                pressure = "100"
            )
        ),
        onClick = {}
    )
}