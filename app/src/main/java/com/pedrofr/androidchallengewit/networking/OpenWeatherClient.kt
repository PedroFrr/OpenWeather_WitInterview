package com.pedrofr.androidchallengewit.networking

import com.pedrofr.androidchallengewit.database.model.Failure
import com.pedrofr.androidchallengewit.database.model.Success
import javax.inject.Inject

class OpenWeatherClient @Inject constructor(
    private val openWeatherService: OpenWeatherService
){

    suspend fun fetchCityCurrentWeather(cityId: Long) =
        try {
            val response = openWeatherService.fetchCityCurrentWeather(cityId = cityId)
            Success(response)
        } catch (error: Throwable) {
            Failure(error)
        }

    suspend fun fetchLocationCurrentWeather(latitude: Double, longitude: Double) =
        try {
            val response = openWeatherService.fetchLocationCurrentWeather(latitude = latitude, longitude = longitude)
            Success(response)
        } catch (error: Throwable) {
            Failure(error)
        }
}