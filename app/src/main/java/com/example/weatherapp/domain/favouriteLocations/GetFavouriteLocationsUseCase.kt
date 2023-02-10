package com.example.weatherapp.domain.favouriteLocations

import com.example.weatherapp.data.favouritelocations.FavouriteLocationsRepository
import com.example.weatherapp.feature.favouritelocations.util.Coordinates
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetFavouriteLocationsUseCase @Inject constructor(
    private val favouriteLocationsRepository: FavouriteLocationsRepository,
) {
    operator fun invoke() = favouriteLocationsRepository.readFavouriteLocations().map { favouriteLocations ->
        favouriteLocations.coordinatesList.map { coordinates ->
            Coordinates(
                latitude = coordinates.lat,
                longitude = coordinates.lon
            )
        }
    }.flowOn(Dispatchers.IO)
}