package com.example.weatherapp.feature.base

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.os.Looper
import android.provider.Settings
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.core.content.ContextCompat.checkSelfPermission
import com.example.weatherapp.baseui.theme.WeatherAppTheme
import com.example.weatherapp.feature.about.screen.AboutScreen
import com.example.weatherapp.feature.base.screen.BaseNavigationScreen
import com.example.weatherapp.feature.favouritelocations.screen.FavouriteLocationsScreen
import com.example.weatherapp.feature.favouritelocations.util.Coordinates
import com.example.weatherapp.feature.mylocation.MyLocationViewModel
import com.example.weatherapp.feature.mylocation.screen.HomeScreen
import com.example.weatherapp.feature.weatherforecast.WeatherForecastActivity
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BaseNavigationActivity : ComponentActivity() {

    private val myLocationViewModel: MyLocationViewModel by viewModels()

    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient

    private val locationPermissionRequest = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        when {
            permissions.getOrDefault(Manifest.permission.ACCESS_FINE_LOCATION, false) ||
                    permissions.getOrDefault(Manifest.permission.ACCESS_COARSE_LOCATION, false) -> {
                getLocation()
            }
            else -> {
                myLocationViewModel.onLocationPermissionDenied()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)

        setContent {
            WeatherAppTheme {
                BaseNavigationScreen(
                    FavouriteScreen = {
                        FavouriteLocationsScreen(
                            onWeatherCardClicked = { coordinates ->
                                navigateToWeatherForecastScreen(coordinates)
                            },
                            onSearchResultSelected = { coordinates ->
                                navigateToWeatherForecastScreen(coordinates)
                            }
                        )
                    },
                    HomeScreen = {
                        HomeScreen(
                            viewModel = myLocationViewModel,
                            onShowWeatherForecastClicked = { coordinates ->
                                navigateToWeatherForecastScreen(coordinates)
                            },
                            onLocationPermissionUpdateClicked = {
                                navigateToAppSettingsScreen()
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
        getUserLocationPermission()
    }

    private fun getUserLocationPermission() {
        when {
            checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
                    && checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED -> {
                getLocation()
            }
            shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_COARSE_LOCATION)
                    || shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION)
            -> {
                myLocationViewModel.onLocationPermissionDenied()
            }
            else -> {
                locationPermissionRequest.launch(
                    arrayOf(
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    )
                )
            }
        }
    }

    @SuppressLint("MissingPermission")
    private fun getLocation() {
        val locationRequest = LocationRequest.Builder(
            Priority.PRIORITY_HIGH_ACCURACY,
            60000
        ).build()

        val locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                super.onLocationResult(locationResult)
                locationResult.lastLocation?.let {
                    myLocationViewModel.getWeatherDetailsForHome(
                        coordinates = Coordinates(
                            "${it.latitude}",
                            "${it.longitude}"
                        )
                    )
                } ?: run {
                    myLocationViewModel.onLocationFetchFailed()
                }
            }
        }
        fusedLocationProviderClient.requestLocationUpdates(
            locationRequest,
            locationCallback,
            Looper.myLooper()
        )
    }

    //region Navigation
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

    private fun navigateToAppSettingsScreen() {
        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
        intent.data = Uri.parse("package:$packageName")
        startActivity(intent)
    }
    //endregion Navigation
}
