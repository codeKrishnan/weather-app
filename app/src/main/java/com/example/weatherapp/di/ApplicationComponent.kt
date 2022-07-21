package com.example.weatherapp.di

import android.content.Context
import com.example.weatherapp.WeatherApplication
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

    fun inject(weatherApplication: WeatherApplication)
}