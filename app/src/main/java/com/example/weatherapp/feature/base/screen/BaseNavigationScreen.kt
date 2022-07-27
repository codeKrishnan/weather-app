package com.example.weatherapp.feature.base.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.weatherapp.baseui.theme.WeatherAppTheme
import com.example.weatherapp.feature.base.screen.widget.NavigationBar
import com.example.weatherapp.feature.favouritelocations.screen.HomeScreen

@Composable
fun BaseNavigationScreen(
    content: @Composable () -> Unit,
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        content = {
            content()
        },
        bottomBar = {
            NavigationBar()
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun BaseNavigationScreenPreview() {
    WeatherAppTheme {
        BaseNavigationScreen {
            HomeScreen(viewModel())
        }
    }
}