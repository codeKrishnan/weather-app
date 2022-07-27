package com.example.weatherapp.feature.weatherforecast

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.weatherapp.WeatherApplication
import com.example.weatherapp.baseui.theme.WeatherAppTheme
import com.example.weatherapp.feature.base.screen.BaseNavigationScreen
import com.example.weatherapp.feature.favouritelocations.util.Coordinates
import com.example.weatherapp.feature.weatherforecast.screen.WeatherForecastScreen
import javax.inject.Inject

class WeatherForecastActivity : ComponentActivity() {

    companion object {

        private const val KEY_COORDINATES = "KEY_COORDINATES"

        fun newIntent(
            activity: Activity,
            coordinates: Coordinates,
        ): Intent {
            return Intent(activity, WeatherForecastActivity::class.java).apply {
                putExtra(KEY_COORDINATES, coordinates)
            }
        }
    }

    @Inject
    lateinit var weatherForecastViewModelFactory: WeatherForecastViewModelFactory

    private val viewModel: WeatherForecastViewModel by viewModels { weatherForecastViewModelFactory }

    @Suppress("DEPRECATION")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpDagger()
        val coordinates =
            requireNotNull(intent.extras?.getParcelable<Coordinates>(KEY_COORDINATES))

        setContent {
            WeatherAppTheme {
                BaseNavigationScreen {
                    WeatherForecastScreen(viewModel = viewModel)
                }
            }
        }
        viewModel.getWeatherForecastOfLocation(
            coordinates = coordinates
        )
    }

    private fun setUpDagger() {
        (application as WeatherApplication).applicationComponent
            .plus()
            .create()
            .inject(this)
    }
}