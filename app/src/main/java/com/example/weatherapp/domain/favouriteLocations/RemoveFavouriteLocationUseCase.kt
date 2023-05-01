package com.example.weatherapp.domain.favouriteLocations

import com.example.weatherapp.data.Coordinates
import com.example.weatherapp.data.favouritelocations.FavouriteLocationsRepository
import javax.inject.Inject

class RemoveFavouriteLocationUseCase @Inject constructor(
    private val favouriteLocationsRepository: FavouriteLocationsRepository,
) {

    suspend operator fun invoke(coordinates: Coordinates): Boolean {
        favouriteLocationsRepository.deleteFavouriteLocation(coordinates = coordinates)
        return true // Replace with proper try catch after testing
    }
}