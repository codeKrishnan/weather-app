package com.example.weatherapp.data.geocoding.repository.base

import com.example.weatherapp.data.common.Result
import com.example.weatherapp.data.geocoding.model.GeocodingAPIResponse

interface GeoCodingRepository {

    /**
     * Retrieves a [List] of [GeocodingAPIResponse] for a search query.
     */
    suspend fun getPlacesForSearchQuery(searchQuery: String): Result<List<GeocodingAPIResponse>>
}