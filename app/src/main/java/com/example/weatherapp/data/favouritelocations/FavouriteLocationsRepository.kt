package com.example.weatherapp.data.favouritelocations

import androidx.datastore.core.DataStore
import com.example.weatherapp.data.Coordinates
import com.example.weatherapp.data.FavouriteLocations
import kotlinx.coroutines.flow.catch
import java.io.IOException
import javax.inject.Inject

class FavouriteLocationsRepository @Inject constructor(
    private val favouriteLocationsDataStore: DataStore<FavouriteLocations>,
) {

    // Create
    suspend fun createFavouriteLocation(
        vararg coordinates: Coordinates,
    ) {
        favouriteLocationsDataStore.updateData {
            FavouriteLocations
                .newBuilder()
                .addAllCoordinates(coordinates.toList())
                .build()
        }
    }

    // Read
    fun readFavouriteLocations() = favouriteLocationsDataStore.data.catch { exception ->
        if (exception is IOException) {
            emit(FavouriteLocations.getDefaultInstance())
        } else {
            throw exception
        }
    }

    // Delete
    suspend fun deleteFavouriteLocation(
        coordinates: Coordinates,
    ) {
        favouriteLocationsDataStore.updateData { favouriteLocations ->
            FavouriteLocations
                .newBuilder()
                .removeCoordinates(favouriteLocations.coordinatesList.indexOf(coordinates))
                .build()
        }
    }
}