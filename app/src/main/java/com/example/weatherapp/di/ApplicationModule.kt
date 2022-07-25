package com.example.weatherapp.di

import com.example.weatherapp.BuildConfig
import com.example.weatherapp.api.common.adapter.WeatherTypeEnumAdapter
import com.example.weatherapp.api.currentweather.repository.CurrentWeatherAPIRepositoryImpl
import com.example.weatherapp.api.currentweather.repository.base.CurrentWeatherRepository
import com.example.weatherapp.api.currentweather.service.CurrentWeatherService
import com.example.weatherapp.api.geocoding.repository.GeoCodingAPIRepositoryImpl
import com.example.weatherapp.api.geocoding.repository.base.GeoCodingRepository
import com.example.weatherapp.api.geocoding.service.GeoCodingAPIService
import com.example.weatherapp.api.weatherforecast.repository.WeatherForecastAPIRepositoryImpl
import com.example.weatherapp.api.weatherforecast.repository.base.WeatherForecastRepository
import com.example.weatherapp.api.weatherforecast.service.WeatherForecastService
import com.example.weatherapp.usecase.currentweather.GetCurrentWeatherUseCaseImpl
import com.example.weatherapp.usecase.currentweather.base.GetCurrentWeatherUseCase
import com.example.weatherapp.usecase.geocoding.GetPlacesForSearchQueryUseCaseImpl
import com.example.weatherapp.usecase.geocoding.base.GetPlacesForSearchQueryUseCase
import com.example.weatherapp.usecase.weatherforecast.GetWeatherForecastForLocationUseCaseImpl
import com.example.weatherapp.usecase.weatherforecast.base.GetWeatherForecastForLocationUseCase
import com.squareup.moshi.Moshi
import dagger.Binds
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module(
    includes = [ApplicationModule.BindsModule::class]
)
class ApplicationModule {

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val builder = OkHttpClient().newBuilder()
        builder.readTimeout(10, TimeUnit.SECONDS)
        builder.connectTimeout(5, TimeUnit.SECONDS)

        if (BuildConfig.DEBUG) {
            val interceptor = HttpLoggingInterceptor()
            interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC)
            builder.addInterceptor(interceptor)
        }
        return builder.build()
    }

    @Singleton
    @Provides
    fun provideCurrentWeatherService(
        okHttpClient: OkHttpClient,
    ): CurrentWeatherService {
        val moshi = Moshi.Builder()
            .add(WeatherTypeEnumAdapter)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org")
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
        return retrofit.create(CurrentWeatherService::class.java)
    }

    @Singleton
    @Provides
    fun provideGeoCodingService(
        okHttpClient: OkHttpClient,
    ): GeoCodingAPIService {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org")
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
        return retrofit.create(GeoCodingAPIService::class.java)
    }

    @Singleton
    @Provides
    fun provideWeatherForecastApiService(
        okHttpClient: OkHttpClient,
    ): WeatherForecastService {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org")
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
        return retrofit.create(WeatherForecastService::class.java)
    }

    @Module
    internal interface BindsModule {

        @Binds
        fun bindCurrentWeatherUseCase(
            getCurrentWeatherUseCaseImpl: GetCurrentWeatherUseCaseImpl,
        ): GetCurrentWeatherUseCase

        @Binds
        fun bindCurrentWeatherAPIRepository(
            currentWeatherAPIRepositoryImpl: CurrentWeatherAPIRepositoryImpl,
        ): CurrentWeatherRepository

        @Binds
        fun bindGetPlacesForSearchQueryUseCase(
            getPlacesForSearchQueryUseCaseImpl: GetPlacesForSearchQueryUseCaseImpl,
        ): GetPlacesForSearchQueryUseCase

        @Binds
        fun bindGeoCodingAPIRepository(
            geoCodingAPIRepositoryImpl: GeoCodingAPIRepositoryImpl,
        ): GeoCodingRepository

        @Binds
        fun bindWeatherForecastAPIRepository(
            weatherForecastAPIRepositoryImpl: WeatherForecastAPIRepositoryImpl,
        ): WeatherForecastRepository

        @Binds
        fun bindGetWeatherForecastForLocationUseCase(
            getWeatherForecastForLocationUseCaseImpl: GetWeatherForecastForLocationUseCaseImpl,
        ): GetWeatherForecastForLocationUseCase
    }
}