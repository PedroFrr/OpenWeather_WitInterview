package com.pedrofr.androidchallengewit.networking

import com.pedrofr.androidchallengewit.BuildConfig
import com.pedrofr.androidchallengewit.database.model.response.GetWeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenWeatherService {

    @GET("weather")
    suspend fun fetchCityCurrentWeather(
        @Query("appId") apiKey: String = BuildConfig.OPEN_WEATHER_API_KEY,
        @Query("id") cityId: Long
    ): GetWeatherResponse

    //TODO maybe add some parameters to exclude so the call doesn't take that much time
    @GET("weather")
    suspend fun fetchLocationCurrentWeather(
        @Query("appId") apiKey: String = BuildConfig.OPEN_WEATHER_API_KEY,
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double
    ): GetWeatherResponse

}