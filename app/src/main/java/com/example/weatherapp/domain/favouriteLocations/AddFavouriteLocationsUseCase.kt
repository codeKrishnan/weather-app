package com.example.weatherapp.domain.favouriteLocations

import com.example.weatherapp.data.Coordinates
import com.example.weatherapp.data.favouritelocations.FavouriteLocationsRepository
import javax.inject.Inject

class AddFavouriteLocationsUseCase @Inject constructor(
    private val repository: FavouriteLocationsRepository,
) {

    suspend fun addFavouriteLocations(
        vararg coordinates: Coordinates,
    ): Boolean {
        repository.createFavouriteLocation(*coordinates)
        return true //TODO: Implement proper try catch after testing
    }
}