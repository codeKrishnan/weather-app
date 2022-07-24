package com.example.weatherapp.api.geocoding.repository.base

import com.example.weatherapp.api.Result
import com.example.weatherapp.api.geocoding.model.GeocodingAPIResponse

interface GeoCodingRepository {

    /**
     * Retrieves a [List] of [GeocodingAPIResponse] for a search query.
     */
    suspend fun getPlacesForSearchQuery(searchQuery: String): Result<List<GeocodingAPIResponse>>
}