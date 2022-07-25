package com.example.weatherapp.feature.base

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.weatherapp.WeatherApplication
import com.example.weatherapp.baseui.theme.WeatherAppTheme
import com.example.weatherapp.feature.base.screen.BaseNavigationScreen
import com.example.weatherapp.feature.favouritelocations.widget.HomeScreen
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
                    HomeScreen(viewModel = viewModel)
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
}
