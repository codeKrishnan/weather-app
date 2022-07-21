package com.example.weatherapp.di

import com.example.weatherapp.api.currentweather.adapter.WeatherTypeEnumAdapter
import com.example.weatherapp.api.currentweather.service.CurrentWeatherService
import com.squareup.moshi.Moshi
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
        val moshi = Moshi.Builder()
            .add(WeatherTypeEnumAdapter)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
        return retrofit.create(CurrentWeatherService::class.java)
    }
}