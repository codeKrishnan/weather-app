package com.example.weatherapp.data.userpreference.repository

import androidx.datastore.core.DataStore
import com.example.weatherapp.data.UserPreferences
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext
import java.io.IOException
import javax.inject.Inject

class UserPreferencesRepository @Inject constructor(
    private val userPreferencesDataStore: DataStore<UserPreferences>,
) {

    private val userPreferences = userPreferencesDataStore.data.catch { exception ->
        if (exception is IOException) {
            emit(UserPreferences.getDefaultInstance())
        } else {
            throw exception
        }
    }

    suspend fun isFavouriteLocationsModified(): Boolean = withContext(Dispatchers.IO) {
        userPreferences.first().isFavouritesModified
    }
}