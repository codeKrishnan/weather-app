package com.example.weatherapp.api.common.adapter

import com.example.weatherapp.feature.favouritelocations.model.WeatherType
import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson

object WeatherTypeEnumAdapter {
    @ToJson
    fun toJson(type: WeatherType): String = type.name

    @FromJson
    fun fromJson(name: String): WeatherType =
        WeatherType.values()
            .find { it.name == name }
            ?: WeatherType.Unknown
}