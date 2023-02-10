package com.example.weatherapp.domain.userpreferences

import com.example.weatherapp.data.userpreference.repository.UserPreferencesRepository
import javax.inject.Inject

class IsFavouritesScreenModifiedUseCase @Inject constructor(
    private val userPreferencesRepository: UserPreferencesRepository,
) {
    suspend operator fun invoke() = userPreferencesRepository.isFavouriteLocationsModified()
}