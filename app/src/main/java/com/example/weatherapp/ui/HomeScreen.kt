package com.example.weatherapp.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.weatherapp.R
import com.example.weatherapp.ui.widget.SearchBar
import com.example.weatherapp.ui.widget.WeatherQuickPreviewWidget

@Composable
fun HomeScreen() {
    Surface(
        color = colorResource(id = R.color.grey_background)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    horizontal = 18.dp,
                    vertical = 24.dp
                ),
        ) {
            SearchBar()
            Spacer(modifier = Modifier.height(18.dp))
            WeatherQuickPreviewWidget()
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    HomeScreen()
}