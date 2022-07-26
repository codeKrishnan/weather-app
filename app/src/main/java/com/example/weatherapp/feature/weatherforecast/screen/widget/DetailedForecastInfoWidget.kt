package com.example.weatherapp.feature.weatherforecast.screen.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
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
import com.example.weatherapp.baseui.widget.DimText
import com.example.weatherapp.baseui.widget.GradientIcon
import com.example.weatherapp.feature.favouritelocations.model.WeatherType

@Composable
fun DetailedForecastInfoWidget() {
    LazyColumn(
        modifier = Modifier
            .background(
                colorResource(id = R.color.grey_background)
            )
    ) {
        items(4) {
            DetailedWeatherInfoRow()
        }
    }
}

@Composable
fun DetailedWeatherInfoRow() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(colorResource(id = R.color.grey_background))
    ) {
        DimText(text = "Today")
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            BrightText(
                modifier = Modifier
                    .width(100.dp),
                text = "Monday"
            )
            GradientIcon(
                modifier = Modifier.size(20.dp),
                weatherType = WeatherType.Clear
            )
            Row {
                BrightText(text = "19°")
                Spacer(modifier = Modifier.size(8.dp))
                DimText(text = "15°")
            }
        }
        LazyRow(
            modifier = Modifier
                .padding(end = 30.dp)
        ) {
            items(10) {
                SingleWeatherInfoItem()
            }
        }
    }
}

@Composable
fun SingleWeatherInfoItem() {
    Column(
        modifier = Modifier
            .background(
                colorResource(id = R.color.grey_background)
            )
            .padding(
                end = 32.dp,
                top = 12.dp,
                bottom = 12.dp
            )
            .heightIn(
                min = 30.dp,
                max = 100.dp
            ),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        BrightText(text = "10 AM")
        GradientIcon(
            modifier = Modifier.size(30.dp),
            weatherType = WeatherType.Clouds
        )
        BrightText(
            text = "19°",
            fontSize = 24.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun DetailedForecastInfoWidgetPreview() {
    WeatherAppTheme {
        DetailedForecastInfoWidget()
    }
}

@Preview(showBackground = true)
@Composable
private fun DetailedWeatherInfoRawPreview() {
    WeatherAppTheme {
        DetailedWeatherInfoRow()
    }
}

@Preview(showBackground = true)
@Composable
private fun SingleWeatherInfoItemPreview() {
    WeatherAppTheme {
        SingleWeatherInfoItem()
    }
}