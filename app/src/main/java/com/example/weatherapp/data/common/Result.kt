package com.example.weatherapp.data.common

/**
 * Result of an API request
 */
sealed class Result<out T> {

    /**
     * Represents a successful [Result]
     */
    data class Success<T>(
        val data: T,
    ) : Result<T>()

    /**
     * Represents a unsuccessful [Result]
     */
    data class Error(val errorData: Any) : Result<Nothing>()
}