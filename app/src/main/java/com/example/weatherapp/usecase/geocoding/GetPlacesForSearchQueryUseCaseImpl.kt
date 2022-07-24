package com.example.weatherapp.usecase.geocoding

import com.example.weatherapp.api.Result
import com.example.weatherapp.api.geocoding.model.GeocodingAPIResponse
import com.example.weatherapp.api.geocoding.repository.base.GeoCodingRepository
import com.example.weatherapp.usecase.geocoding.base.GetPlacesForSearchQueryUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetPlacesForSearchQueryUseCaseImpl(
    private val geoCodingRepository: GeoCodingRepository,
) : GetPlacesForSearchQueryUseCase {

    override suspend fun invoke(searchQuery: String): Result<List<GeocodingAPIResponse>> =
        withContext(Dispatchers.Default) {
            geoCodingRepository.getPlacesForSearchQuery(searchQuery)
        }
}