package com.example.weatherapp.feature.base

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.weatherapp.WeatherApplication
import com.example.weatherapp.baseui.theme.WeatherAppTheme
import com.example.weatherapp.feature.about.screen.AboutScreen
import com.example.weatherapp.feature.base.screen.BaseNavigationScreen
import com.example.weatherapp.feature.favouritelocations.screen.FavouriteLocationsScreen
import com.example.weatherapp.feature.favouritelocations.util.Coordinates
import com.example.weatherapp.feature.home.screen.HomeScreen
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
                BaseNavigationScreen(
                    FavouriteScreen = {
                        FavouriteLocationsScreen(
                            viewModel = viewModel,
                            onWeatherCardClicked = { coordinates ->
                                navigateToWeatherForecastScreen(coordinates)
                            }
                        )
                    },
                    HomeScreen = {
                        HomeScreen(
                            viewModel = viewModel,
                            onShowWeatherForecastClicked = { coordinates ->
                                navigateToWeatherForecastScreen(coordinates)
                            }
                        )
                    },
                    AboutScreen = {
                        AboutScreen(onGoClicked = { url ->
                            navigateToExternalBrowser(url)
                        })
                    }
                )
            }
        }
        viewModel.getWeatherInformationOfFavouriteLocations()
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

    private fun navigateToExternalBrowser(url: String) {
        val openURL = Intent(Intent.ACTION_VIEW)
        openURL.data = Uri.parse(url)
        startActivity(openURL)
    }
}
