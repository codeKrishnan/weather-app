package com.example.weatherapp.feature.base

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.weatherapp.WeatherApplication
import com.example.weatherapp.baseui.theme.WeatherAppTheme
import com.example.weatherapp.feature.base.screen.BaseNavigationScreen
import com.example.weatherapp.feature.favouritelocations.screen.HomeScreen
import com.example.weatherapp.feature.favouritelocations.util.Coordinates
import com.example.weatherapp.feature.weatherforecast.WeatherForecastActivity
import javax.inject.Inject


class BaseNavigationActivity : ComponentActivity() {

    @Inject
    lateinit var favouriteLocationViewModelFactory: BaseNavigationViewModelFactory

    private val viewModel: BaseNavigationViewModel by viewModels { favouriteLocationViewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpDagger()
        setContent {
            WeatherAppTheme {
                BaseNavigationScreen {
                    HomeScreen(
                        viewModel = viewModel,
                        onWeatherCardClicked = {
                            navigateToWeatherForecastScreen(it)
                        }
                    )
                }
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

    private fun navigateToWeatherForecastScreen(coordinates: Coordinates) {
        val intent = WeatherForecastActivity.newIntent(
            activity = this,
            coordinates = coordinates
        )
        this.startActivity(intent)
    }
}
