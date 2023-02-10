package com.example.weatherapp.data.favouritelocations

import androidx.datastore.core.DataStore
import com.example.weatherapp.data.FavouriteLocations
import kotlinx.coroutines.flow.catch
import java.io.IOException
import javax.inject.Inject

class FavouriteLocationsRepository @Inject constructor(
    private val favouriteLocationsDataStore: DataStore<FavouriteLocations>,
) {

    fun readFavouriteLocations() = favouriteLocationsDataStore.data.catch { exception ->
        if (exception is IOException) {
            emit(FavouriteLocations.getDefaultInstance())
        } else {
            throw exception
        }
    }
}