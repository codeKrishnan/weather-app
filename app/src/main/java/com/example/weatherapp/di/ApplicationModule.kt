package com.example.weatherapp.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.dataStoreFile
import com.example.weatherapp.BuildConfig
import com.example.weatherapp.data.FavouriteLocations
import com.example.weatherapp.data.UserPreferences
import com.example.weatherapp.data.common.adapter.WeatherTypeEnumAdapter
import com.example.weatherapp.data.currentweather.repository.CurrentWeatherAPIRepositoryImpl
import com.example.weatherapp.data.currentweather.repository.base.CurrentWeatherRepository
import com.example.weatherapp.data.currentweather.service.CurrentWeatherService
import com.example.weatherapp.data.favouritelocations.FavouriteLocationsDeserializer
import com.example.weatherapp.data.geocoding.repository.GeoCodingAPIRepositoryImpl
import com.example.weatherapp.data.geocoding.repository.base.GeoCodingRepository
import com.example.weatherapp.data.geocoding.service.GeoCodingAPIService
import com.example.weatherapp.data.userpreference.UserPreferenceSerializer
import com.example.weatherapp.data.weatherforecast.repository.WeatherForecastAPIRepositoryImpl
import com.example.weatherapp.data.weatherforecast.repository.base.WeatherForecastRepository
import com.example.weatherapp.data.weatherforecast.service.WeatherForecastService
import com.example.weatherapp.domain.currentweather.GetCurrentWeatherUseCaseImpl
import com.example.weatherapp.domain.currentweather.base.GetCurrentWeatherUseCase
import com.example.weatherapp.domain.geocoding.GetPlacesForSearchQueryUseCaseImpl
import com.example.weatherapp.domain.geocoding.base.GetPlacesForSearchQueryUseCase
import com.example.weatherapp.domain.weatherforecast.GetWeatherForecastForLocationUseCaseImpl
import com.example.weatherapp.domain.weatherforecast.base.GetWeatherForecastForLocationUseCase
import com.squareup.moshi.Moshi
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
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

    @Singleton
    @Provides
    fun provideUserPreferencesDataStore(
        @ApplicationContext context: Context,
    ): DataStore<UserPreferences> {
        return DataStoreFactory.create(
            serializer = UserPreferenceSerializer,
            produceFile = {
                context.dataStoreFile(USER_PREFERENCES_DATA_STORE_FILE_NAME)
            }
        )
    }

    @Singleton
    @Provides
    fun provideFavouriteLocationsDataStore(
        @ApplicationContext applicationContext: Context,
    ): DataStore<FavouriteLocations> {
        return DataStoreFactory.create(
            serializer = FavouriteLocationsDeserializer,
            produceFile = {
                applicationContext.dataStoreFile(FAVOURITES_LOCATIONS_DATA_STORE_FILE_NAME)
            }
        )
    }

    @Module
    @InstallIn(SingletonComponent::class)
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

    companion object {
        private const val USER_PREFERENCES_DATA_STORE_FILE_NAME = "user_preferences.pb"
        private const val FAVOURITES_LOCATIONS_DATA_STORE_FILE_NAME = "favourite_locations.db"
    }
}