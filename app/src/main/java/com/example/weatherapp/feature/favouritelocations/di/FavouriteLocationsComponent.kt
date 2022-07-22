package com.example.weatherapp.feature.favouritelocations.di

import com.example.weatherapp.feature.favouritelocations.FavouriteLocationsActivity
import dagger.Subcomponent
import javax.inject.Scope

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


@Scope
@MustBeDocumented
@Retention(AnnotationRetention.RUNTIME)
annotation class FeatureScope