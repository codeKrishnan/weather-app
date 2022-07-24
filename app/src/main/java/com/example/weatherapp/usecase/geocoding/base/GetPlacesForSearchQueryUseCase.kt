package com.example.weatherapp.usecase.geocoding.base

import com.example.weatherapp.api.Result
import com.example.weatherapp.api.geocoding.model.GeocodingAPIResponse

interface GetPlacesForSearchQueryUseCase {

    suspend operator fun invoke(searchQuery: String): Result<List<GeocodingAPIResponse>>
}