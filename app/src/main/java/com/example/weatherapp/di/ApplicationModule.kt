package com.example.weatherapp.di

import com.example.weatherapp.api.currentweather.service.CurrentWeatherService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
class ApplicationModule {

    @Singleton
    @Provides
    fun provideCurrentWeatherRepository(): CurrentWeatherService {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
        return retrofit.create(CurrentWeatherService::class.java)
    }
}