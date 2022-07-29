package com.example.weatherapp.feature.home.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherapp.R
import com.example.weatherapp.baseui.theme.WeatherAppTheme
import com.example.weatherapp.baseui.widget.BlueCallOut
import com.example.weatherapp.baseui.widget.BrightText
import com.example.weatherapp.baseui.widget.DimText
import com.example.weatherapp.feature.favouritelocations.model.ShortWeatherInfo
import com.example.weatherapp.feature.favouritelocations.model.WeatherType
import com.example.weatherapp.feature.favouritelocations.model.getImage
import com.example.weatherapp.feature.favouritelocations.screen.widget.IconText
import com.example.weatherapp.feature.favouritelocations.util.Coordinates
import com.example.weatherapp.baseui.widget.QuickWeatherInfoBar
import com.example.weatherapp.util.getCurrentDate

@Composable
fun HomeScreen() {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.grey_background))
            .padding(18.dp)
    ) {
        Column(
            modifier = Modifier
                .background(colorResource(id = R.color.grey_background)),
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
                fontSize = 12.sp
            )
            BrightText(text = "San Fransisco, USA")
            Spacer(modifier = Modifier.size(24.dp))
            Image(
                painter = painterResource(id = WeatherType.Thunderstorm.getImage()),
                contentDescription = null
            )
            Spacer(modifier = Modifier.size(24.dp))
            BlueCallOut(label = "Moonlight")
            Spacer(modifier = Modifier.size(24.dp))
            BrightText(
                text = "20°c",
                fontSize = 64.sp
            )
            Spacer(modifier = Modifier.size(24.dp))
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
                    sunsetOrRaiseTime = "6:23 AM"
                ),
            )
        }
    }
}


@Preview
@Composable
fun HomeScreenPreview() {
    WeatherAppTheme {
        HomeScreen()
    }
}