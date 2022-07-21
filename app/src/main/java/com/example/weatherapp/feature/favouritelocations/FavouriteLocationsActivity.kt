package com.example.weatherapp.feature.favouritelocations

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.weatherapp.feature.favouritelocations.widget.HomeScreen
import com.example.weatherapp.ui.theme.WeatherAppTheme

class FavouriteLocationsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherAppTheme {
                HomeScreen()
            }
        }
    }
}
