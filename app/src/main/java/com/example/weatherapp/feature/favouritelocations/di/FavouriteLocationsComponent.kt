package com.example.weatherapp.feature.favouritelocations.di

import com.example.weatherapp.di.FeatureScope
import com.example.weatherapp.feature.favouritelocations.FavouriteLocationsActivity
import dagger.Subcomponent

@FeatureScope
@Subcomponent(
    modules = [FavouriteLocationModule::class]
)
interface FavouriteLocationsComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): FavouriteLocationsComponent
    }

    fun inject(favouriteLocationsActivity: FavouriteLocationsActivity)
}