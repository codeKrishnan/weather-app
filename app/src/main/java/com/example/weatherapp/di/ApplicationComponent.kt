package com.example.weatherapp.di

import android.content.Context
import com.example.weatherapp.WeatherApplication
import com.example.weatherapp.feature.base.di.BaseNavigationComponent
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
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

    fun plus(): BaseNavigationComponent.Factory

    fun inject(weatherApplication: WeatherApplication)
}