package com.example.weatherapp.feature.weatherforecast.screen.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherapp.R
import com.example.weatherapp.baseui.widget.BrightText
import com.example.weatherapp.feature.favouritelocations.model.ShortWeatherInfo
import kotlin.math.roundToInt

@Composable
fun Header(shortWeatherInfo: ShortWeatherInfo) {
    with(shortWeatherInfo) {
        BrightText(
            text = cityName,
            fontSize = 20.sp
        )
        BrightText(
            text = "${currentTemperature.roundToInt()}°",
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
}