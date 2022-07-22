package com.example.weatherapp.di

import android.content.Context
import com.example.weatherapp.WeatherApplication
import com.example.weatherapp.feature.favouritelocations.di.FavouriteLocationsComponent
import dagger.BindsInstance
import dagger.Component

@Component(
    modules = [ApplicationModule::class]
)
interface ApplicationComponent {

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance context: Context,
        ): ApplicationComponent
    }

    fun plus(): FavouriteLocationsComponent.Factory

    fun inject(weatherApplication: WeatherApplication)
}