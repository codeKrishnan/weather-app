package com.example.weatherapp.data.favouritelocations

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import com.example.weatherapp.data.FavouriteLocations
import com.google.protobuf.InvalidProtocolBufferException
import java.io.InputStream
import java.io.OutputStream

object FavouriteLocationsDeserializer : Serializer<FavouriteLocations> {
    override val defaultValue: FavouriteLocations
        get() = FavouriteLocations.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): FavouriteLocations {
        try {
            return FavouriteLocations.parseFrom(input)
        } catch (exception: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto.", exception)
        }
    }

    override suspend fun writeTo(t: FavouriteLocations, output: OutputStream) {
        return t.writeTo(output)
    }

}