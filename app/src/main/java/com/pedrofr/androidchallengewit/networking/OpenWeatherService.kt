package com.pedrofr.androidchallengewit.networking

import com.pedrofr.androidchallengewit.BuildConfig
import com.pedrofr.androidchallengewit.database.model.response.GetWeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenWeatherService {

    @GET("weather")
    suspend fun fetchCityCurrentWeather(
        @Query("appId") apiKey: String = BuildConfig.OPEN_WEATHER_API_KEY,
        @Query("units") units: String = "metric",
        @Query("id") cityId: Long
    ): GetWeatherResponse

    @GET("weather")
    suspend fun fetchLocationCurrentWeather(
        @Query("appId") apiKey: String = BuildConfig.OPEN_WEATHER_API_KEY,
        @Query("units") units: String = "metric",
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double
    ): GetWeatherResponse

}