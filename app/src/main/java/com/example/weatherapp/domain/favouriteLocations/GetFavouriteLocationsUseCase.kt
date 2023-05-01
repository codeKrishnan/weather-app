package com.example.weatherapp.domain.favouriteLocations

import com.example.weatherapp.data.favouritelocations.FavouriteLocationsRepository
import com.example.weatherapp.domain.userpreferences.IsFavouritesScreenModifiedUseCase
import com.example.weatherapp.feature.favouritelocations.util.Coordinates
import com.example.weatherapp.feature.favouritelocations.util.SavedLocations
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetFavouriteLocationsUseCase @Inject constructor(
    private val favouriteLocationsRepository: FavouriteLocationsRepository,
    private val isFavouritesScreenModifiedUseCase: IsFavouritesScreenModifiedUseCase,
) {
    @OptIn(FlowPreview::class)
    operator fun invoke(): Flow<List<Coordinates>> {
        return favouriteLocationsRepository.readFavouriteLocations().map { favouriteLocations ->
            favouriteLocations.coordinatesList.map { coordinates ->
                Coordinates(
                    latitude = coordinates.lat,
                    longitude = coordinates.lon
                )
            }
        }.flatMapConcat { favouriteLocations ->
            flow {
                if (favouriteLocations.isEmpty() && !isFavouritesScreenModifiedUseCase()) {
                    emit(SavedLocations.defaultLocations)
                } else {
                    emit(favouriteLocations)
                }
            }
        }.flowOn(Dispatchers.IO)
    }
}

