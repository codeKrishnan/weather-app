package com.example.weatherapp.feature.home.util

import com.example.weatherapp.feature.favouritelocations.util.Coordinates

/**
 * State holder for Home page screen.
 */
class HomePageState {
    var isLocationAlreadyUpdated = false
    var retrievedCoordinates: Coordinates? = null
}
