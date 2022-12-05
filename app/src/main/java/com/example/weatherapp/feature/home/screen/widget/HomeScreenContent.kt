package com.example.weatherapp.feature.home.screen.widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherapp.R
import com.example.weatherapp.baseui.theme.SkyBlue
import com.example.weatherapp.baseui.theme.WeatherAppTheme
import com.example.weatherapp.baseui.widget.BlueCallOut
import com.example.weatherapp.baseui.widget.BrightText
import com.example.weatherapp.baseui.widget.DimText
import com.example.weatherapp.baseui.widget.QuickWeatherInfoBar
import com.example.weatherapp.feature.favouritelocations.model.ShortWeatherInfo
import com.example.weatherapp.feature.favouritelocations.model.WeatherType
import com.example.weatherapp.feature.favouritelocations.model.getImage
import com.example.weatherapp.feature.favouritelocations.screen.widget.IconText
import com.example.weatherapp.feature.favouritelocations.util.Coordinates
import com.example.weatherapp.feature.home.screen.Bullet
import com.example.weatherapp.util.getCurrentDate
import kotlin.math.roundToInt

@Composable
fun HomeScreenContent(
    shortWeatherInfo: ShortWeatherInfo,
    onShowWeatherForecastClicked: (Coordinates) -> Unit,
) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                vertical = 18.dp,
                horizontal = 8.dp
            )
            .verticalScroll(
                state = rememberScrollState(),
            )
    ) {
        Column(
            modifier = Modifier,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            IconText(
                iconResource = R.drawable.gps,
                text = "Your Location Now",
                isBrightText = false
            )
            Spacer(modifier = Modifier.size(12.dp))
            BrightText(text = "Today")
            DimText(
                text = getCurrentDate(),
                fontSize = 14.sp
            )
            Spacer(modifier = Modifier.size(8.dp))
            BrightText(text = "${shortWeatherInfo.cityName}, ${shortWeatherInfo.countryCode}")
            Spacer(modifier = Modifier.size(24.dp))
            Image(
                painter = painterResource(id = shortWeatherInfo.weatherType.getImage()),
                contentDescription = null
            )
            Spacer(modifier = Modifier.size(24.dp))
            BlueCallOut(label = shortWeatherInfo.weatherDescription)
            Spacer(modifier = Modifier.size(24.dp))
            BrightText(
                text = "${shortWeatherInfo.currentTemperature.roundToInt()}°c",
                fontSize = 64.sp
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                BrightText(text = "Feels Like ${shortWeatherInfo.feelsLikeTemperature}°c")
                Bullet()
                val sunriseOrSetLabel = if (shortWeatherInfo.isSunrise) {
                    "Sunrise"
                } else {
                    "Sunset"
                }
                BrightText(text = "$sunriseOrSetLabel ${shortWeatherInfo.sunsetOrRaiseTime}")
            }
            Spacer(modifier = Modifier.size(24.dp))
            QuickWeatherInfoBar(
                shortWeatherInfo = shortWeatherInfo
            )
            Spacer(modifier = Modifier.size(12.dp))
            Row(modifier = Modifier
                .clickable {
                    onShowWeatherForecastClicked(
                        shortWeatherInfo.coordinates
                    )
                },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier
                        .padding(end = 12.dp),
                    fontSize = 18.sp,
                    text = "Next 5 days",
                    color = SkyBlue
                )
                Icon(
                    modifier = Modifier
                        .size(16.dp)
                        .padding(top = 4.dp),
                    tint = SkyBlue,
                    painter = painterResource(id = R.drawable.right_arrow),
                    contentDescription = "Weather forecast for next five days"
                )

            }
            Spacer(modifier = Modifier.size(64.dp))
            SourceDeclarationBar()
        }
    }
}

@Preview
@Composable
fun HomeScreenContentPreview() {
    WeatherAppTheme {
        HomeScreenContent(
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
                sunsetOrRaiseTime = "6:23 AM",
                feelsLikeTemperature = "23"
            ),
            onShowWeatherForecastClicked = {}
        )
    }
}