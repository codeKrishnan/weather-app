package com.example.weatherapp.feature.weatherforecast

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.weatherapp.baseui.theme.WeatherAppTheme
import com.example.weatherapp.feature.favouritelocations.util.Coordinates
import com.example.weatherapp.feature.weatherforecast.screen.WeatherForecastScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
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

    private val viewModel: WeatherForecastViewModel by viewModels()

    @Suppress("DEPRECATION")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val coordinates =
            requireNotNull(intent.extras?.getParcelable<Coordinates>(KEY_COORDINATES))

        setContent {
            WeatherAppTheme {
                WeatherForecastScreen(viewModel = viewModel)

            }
        }
        viewModel.getWeatherForecastOfLocation(
            coordinates = coordinates
        )
    }
}