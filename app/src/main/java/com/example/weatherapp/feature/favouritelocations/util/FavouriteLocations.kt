package com.example.weatherapp.feature.favouritelocations.util

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class Coordinates(
    val latitude: String,
    val longitude: String,
) : Parcelable

object FavouriteLocations {

    val defaultLocations = listOf(
        Coordinates(
            latitude = "38.89",
            longitude = "-77.02",
        ),
        Coordinates(
            latitude = "40.25",
            longitude = "-3.45",
        ),
        Coordinates(
            latitude = "15.5796",
            longitude = "31.0633",
        ),
        Coordinates(
            latitude = "53.39",
            longitude = "-2.554",
        ),
        Coordinates(
            latitude = "19.07",
            longitude = "72.877",
        ),
        Coordinates(
            latitude = "48.85",
            longitude = "2.32",
        ),
        Coordinates(
            latitude = "35.68",
            longitude = "139.75",
        ),
        Coordinates(
            latitude = "55.75",
            longitude = "37.61",
        ),
    )
}