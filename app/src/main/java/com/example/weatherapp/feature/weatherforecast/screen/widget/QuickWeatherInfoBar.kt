package com.example.weatherapp.feature.weatherforecast.screen.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.weatherapp.R
import com.example.weatherapp.feature.favouritelocations.screen.widget.IconText

@Composable
fun QuickWeatherInfoBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(colorResource(id = R.color.grey_background)),
        horizontalArrangement = Arrangement.SpaceBetween

    ) {
        IconText(iconResource = R.drawable.droplet, text = "13%")
        IconText(iconResource = R.drawable.wind, text = "9 m/s")
        IconText(iconResource = R.drawable.wind, text = "9 m/s")
    }
}

@Preview(showBackground = true)
@Composable
private fun QuickWeatherInfoBarPreview() {
    QuickWeatherInfoBar()
}