package com.example.weatherapp.feature.favouritelocations.screen.widget

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.weatherapp.baseui.theme.WeatherAppTheme

@Composable
fun LoadingIndicator() {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LinearProgressIndicator()
    }
}

@Preview(showBackground = true)
@Composable
private fun LoadingIndicatorPreview() {
    WeatherAppTheme {
        LoadingIndicator()
    }
}