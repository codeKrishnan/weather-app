package com.example.weatherapp.data.userpreference.repository

import androidx.datastore.core.DataStore
import com.example.weatherapp.data.UserPreferences
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UserPreferencesRepository @Inject constructor(
    private val userPreferencesDataStore: DataStore<UserPreferences>,
) {
    suspend fun isFavouriteLocationsModified(): Boolean = withContext(Dispatchers.IO) {
        userPreferencesDataStore.data.first().isFavouritesModified
    }
}