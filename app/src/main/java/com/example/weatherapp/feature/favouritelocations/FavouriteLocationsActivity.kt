package com.example.weatherapp.feature.favouritelocations

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.weatherapp.WeatherApplication
import com.example.weatherapp.feature.favouritelocations.widget.HomeScreen
import com.example.weatherapp.ui.theme.WeatherAppTheme
import javax.inject.Inject


class FavouriteLocationsActivity : ComponentActivity() {

    @Inject
    lateinit var favouriteLocationViewModelFactory: FavouriteLocationsViewModelFactory

    private val viewModel: FavouriteLocationsViewModel by viewModels { favouriteLocationViewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpDagger()
        setContent {
            WeatherAppTheme {
                HomeScreen()
            }
        }
        viewModel.getCurrentWeather()
    }

    private fun setUpDagger() {
        (application as WeatherApplication).applicationComponent
            .plus()
            .create()
            .inject(this)
    }
}
