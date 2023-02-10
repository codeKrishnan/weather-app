package com.example.weatherapp.data.geocoding.service

import com.example.weatherapp.data.geocoding.model.GeocodingAPIResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GeoCodingAPIService {

    @GET("/geo/1.0/direct")
    suspend fun getPlacesForSearchQuery(
        @Query("appid") apiKey: String = "ee4c3053bf9f27c8e39738d9b555f945",
        @Query("limit") limit: Int = 5,
        @Query("q") searchQuery: String,
    ): Response<List<GeocodingAPIResponse>>
}