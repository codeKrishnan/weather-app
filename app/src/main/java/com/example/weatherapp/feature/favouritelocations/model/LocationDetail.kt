package com.example.weatherapp.feature.favouritelocations.model

import com.example.weatherapp.api.geocoding.model.GeocodingAPIResponse

data class LocationDetail(
    val cityName: String,
    val latitude: String,
    val longitude: String,
)


fun GeocodingAPIResponse.toLocationDetail(): LocationDetail {
    return LocationDetail(
        cityName = "$name, $country",
        latitude = lat,
        longitude = lon,
    )
}