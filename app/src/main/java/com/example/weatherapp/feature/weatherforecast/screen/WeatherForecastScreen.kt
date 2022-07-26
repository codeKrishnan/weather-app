package com.example.weatherapp.feature.weatherforecast.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherapp.R
import com.example.weatherapp.baseui.widget.BrightText
import com.example.weatherapp.feature.weatherforecast.screen.widget.DetailedForecastInfoWidget
import com.example.weatherapp.feature.weatherforecast.screen.widget.QuickWeatherInfoBar

@Composable
fun WeatherForecastScreen() {
    Column{

    }
    Column(
        modifier = androidx.compose.ui.Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.grey_background))
            .padding(
                vertical = 32.dp,
                horizontal = 32.dp,
            )
    ) {
        BrightText(
            text = "San Fransisco",
            fontSize = 20.sp
        )
        BrightText(
            text = "18°",
            fontSize = 64.sp
        )
        BrightText(
            modifier = androidx.compose.ui.Modifier
                .background(
                    color = colorResource(id = R.color.violet_pill_background),
                    shape = RoundedCornerShape(18.dp)
                )
                .padding(
                    horizontal = 12.dp,
                    vertical = 8.dp
                ),
            text = "Cloudy",

            )
        Spacer(modifier = Modifier.height(30.dp))
        QuickWeatherInfoBar()
        Spacer(modifier = Modifier.height(60.dp))
        DetailedForecastInfoWidget()
    }
}

@Preview(showBackground = true)
@Composable
private fun WeatherForecastScreenPreview() {
    WeatherForecastScreen()
}