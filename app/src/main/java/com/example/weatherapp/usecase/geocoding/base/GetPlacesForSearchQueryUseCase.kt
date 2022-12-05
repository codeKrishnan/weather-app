package com.example.weatherapp.usecase.geocoding.base

import com.example.weatherapp.data.common.Result
import com.example.weatherapp.feature.favouritelocations.model.LocationDetail

interface GetPlacesForSearchQueryUseCase {

    suspend operator fun invoke(searchQuery: String): Result<List<LocationDetail>>
}