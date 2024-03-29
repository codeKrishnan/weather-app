package com.example.weatherapp.domain.geocoding

import com.example.weatherapp.data.common.Result
import com.example.weatherapp.data.geocoding.repository.base.GeoCodingRepository
import com.example.weatherapp.domain.geocoding.base.GetPlacesForSearchQueryUseCase
import com.example.weatherapp.feature.favouritelocations.model.LocationDetail
import com.example.weatherapp.feature.favouritelocations.model.toLocationDetail
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetPlacesForSearchQueryUseCaseImpl @Inject constructor(
    private val geoCodingRepository: GeoCodingRepository,
) : GetPlacesForSearchQueryUseCase {

    override suspend fun invoke(searchQuery: String): Result<List<LocationDetail>> =
        withContext(Dispatchers.Default) {
            return@withContext when (val result =
                geoCodingRepository.getPlacesForSearchQuery(searchQuery)) {
                is Result.Error -> {
                    Result.Error(result.errorData)
                }
                is Result.Success -> {
                    Result.Success(
                        result.data.map {
                            it.toLocationDetail()
                        }
                    )
                }
            }
        }
}