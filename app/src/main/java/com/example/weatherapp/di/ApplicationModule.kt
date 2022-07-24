package com.example.weatherapp.di

import com.example.weatherapp.BuildConfig
import com.example.weatherapp.api.currentweather.adapter.WeatherTypeEnumAdapter
import com.example.weatherapp.api.currentweather.repository.CurrentWeatherRepositoryImpl
import com.example.weatherapp.api.currentweather.repository.base.CurrentWeatherRepository
import com.example.weatherapp.api.currentweather.service.CurrentWeatherService
import com.example.weatherapp.usecase.currentweather.GetCurrentWeatherUseCaseImpl
import com.example.weatherapp.usecase.currentweather.base.GetCurrentWeatherUseCase
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

    @Module
    internal interface BindsModule {

        @Binds
        fun bindCurrentWeatherUseCaseImpl(
            getCurrentWeatherUseCaseImpl: GetCurrentWeatherUseCaseImpl,
        ): GetCurrentWeatherUseCase

        @Binds
        fun bindCurrentWeatherRepository(
            currentWeatherRepositoryImpl: CurrentWeatherRepositoryImpl,
        ): CurrentWeatherRepository
    }
}