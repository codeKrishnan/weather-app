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
import com.example.weatherapp.feature.weatherforecast.screen.WeatherForecastScreen
import javax.inject.Inject

class WeatherForecastActivity : ComponentActivity() {

    companion object {

        fun newIntent(
            activity: Activity,
        ): Intent = Intent(activity, WeatherForecastActivity::class.java)
    }

    @Inject
    lateinit var weatherForecastViewModelFactory: WeatherForecastViewModelFactory

    private val viewModel: WeatherForecastViewModel by viewModels { weatherForecastViewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpDagger()
        setContent {
            WeatherAppTheme {
                BaseNavigationScreen {
                    WeatherForecastScreen(viewModel = viewModel)
                }
            }
        }
    }

    private fun setUpDagger() {
        (application as WeatherApplication).applicationComponent
            .plus()
            .create()
            .inject(this)
    }
}