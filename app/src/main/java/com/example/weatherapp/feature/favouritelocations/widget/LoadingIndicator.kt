package com.example.weatherapp.feature.favouritelocations.widget

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherapp.baseui.theme.WeatherAppTheme

@Composable
fun LoadingIndicator() {
    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LinearProgressIndicator(
                modifier = Modifier
                    .height(10.dp)
                    .padding(bottom = 16.dp),
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                fontSize = 16.sp,
                text = "Please wait while we load the weather information for you!"
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun LoadingIndicatorPreview() {
    WeatherAppTheme {
        LoadingIndicator()
    }
}