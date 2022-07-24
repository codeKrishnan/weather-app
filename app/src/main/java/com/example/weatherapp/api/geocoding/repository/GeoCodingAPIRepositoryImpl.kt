package com.example.weatherapp.api.geocoding.repository

import com.example.weatherapp.api.Result
import com.example.weatherapp.api.geocoding.model.GeocodingAPIResponse
import com.example.weatherapp.api.geocoding.repository.base.GeoCodingRepository
import com.example.weatherapp.api.geocoding.service.GeoCodingAPIService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.SocketTimeoutException
import javax.inject.Inject

class GeoCodingAPIRepositoryImpl @Inject constructor(
    private val geoCodingAPIService: GeoCodingAPIService,
) : GeoCodingRepository {

    override suspend fun getPlacesForSearchQuery(
        searchQuery: String,
    ): Result<List<GeocodingAPIResponse>> =

        withContext(Dispatchers.IO) {
            try {
                val response = geoCodingAPIService.getPlacesForSearchQuery(
                    searchQuery = searchQuery
                )

                return@withContext if (response.isSuccessful && response.body() != null) {
                    Result.Success(response.body()!!)
                } else {
                    Result.Error("Couldn't fetch locations: ${response.errorBody()}")
                }

            } catch (exception: Exception) {
                when (exception) {
                    is SocketTimeoutException -> {
                        Result.Error("Couldn't fetch locations: ${exception.message ?: ""}")
                    }
                    else -> throw exception
                }
            }
        }
}
