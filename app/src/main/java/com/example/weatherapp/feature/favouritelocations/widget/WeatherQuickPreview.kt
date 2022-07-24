package com.example.weatherapp.feature.favouritelocations.widget

import androidx.annotation.DrawableRes
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
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
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.weatherapp.R
import com.example.weatherapp.baseui.widget.BrightText
import com.example.weatherapp.baseui.widget.BrightTextLarge
import com.example.weatherapp.baseui.widget.DimText
import com.example.weatherapp.feature.favouritelocations.model.ShortWeatherInfo
import com.example.weatherapp.feature.favouritelocations.model.WeatherType
import kotlin.math.roundToInt


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun WeatherQuickPreviewWidget(
    shortWeatherItems: List<ShortWeatherInfo>,
) {
    LazyVerticalGrid(
        cells = GridCells.Adaptive(minSize = 150.dp),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        items(shortWeatherItems.size) {
            WeatherQuickPreviewCard(
                shortWeatherItems[it]
            )
        }
    }
}


@Composable
private fun WeatherQuickPreviewCard(
    shortWeatherInfo: ShortWeatherInfo,
) {
    Surface(
        modifier = Modifier
            .aspectRatio(1f)
            .padding(8.dp)
            .wrapContentSize(),
        color = colorResource(id = R.color.grey_cards),
        shape = RoundedCornerShape(16.dp)
    ) {
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
                Column(
                    verticalArrangement = Arrangement.Top
                ) {

                    val colorGradient = Brush.verticalGradient(
                        listOf(Color.Blue, Color.Magenta)
                    )

                    Icon(
                        modifier = Modifier
                            .graphicsLayer(alpha = 0.99f)
                            .drawWithCache {
                                onDrawWithContent {
                                    drawContent()
                                    drawRect(colorGradient, blendMode = BlendMode.SrcAtop)
                                }
                            },
                        painter = painterResource(id = R.drawable.cloudy),
                        contentDescription = "Weather image",
                    )
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconRepresentation(
                    iconResource = R.drawable.wind,
                    text = shortWeatherInfo.windSpeed
                )
                IconRepresentation(
                    iconResource = R.drawable.droplet,
                    text = shortWeatherInfo.humidity
                )
            }
        }
    }
}


@Composable
fun IconRepresentation(
    @DrawableRes iconResource: Int,
    text: String,
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
            tint = colorResource(id = R.color.blue_icon_tint)
        )
        BrightText(
            text = text,
        )

    }
}

@Preview(showBackground = true)
@Composable
fun WeatherQuickPreviewPreview() {
    WeatherQuickPreviewCard(
        ShortWeatherInfo(
            currentTemperature = 25.00,
            countryCode = "IND",
            cityName = "Delhi",
            windSpeed = "2",
            humidity = "23",
            weatherType = WeatherType.Clear
        )
    )
}

@Preview
@Composable
fun WeatherQuickPreviewWidgetPreview() {
    WeatherQuickPreviewWidget(
        shortWeatherItems = listOf(
            ShortWeatherInfo(
                currentTemperature = 25.00,
                countryCode = "IND",
                cityName = "Delhi",
                windSpeed = "2",
                humidity = "23",
                weatherType = WeatherType.Clear
            ),
            ShortWeatherInfo(
                currentTemperature = 25.00,
                countryCode = "IND",
                cityName = "Delhi",
                windSpeed = "2",
                humidity = "23",
                weatherType = WeatherType.Clear
            )
        )
    )
}