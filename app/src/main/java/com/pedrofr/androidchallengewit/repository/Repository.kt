package com.pedrofr.androidchallengewit.repository

import com.pedrofr.androidchallengewit.database.model.City
import com.pedrofr.androidchallengewit.database.model.Result
import com.pedrofr.androidchallengewit.database.model.Weather
import com.pedrofr.androidchallengewit.database.model.response.GetWeatherResponse
import kotlinx.coroutines.flow.Flow

interface Repository {

    fun fetchCities(): Flow<List<City>>
    suspend fun fetchCity(cityId: Long): City
    suspend fun fetchCityCurrentWeather(cityId: Long): Flow<Result<GetWeatherResponse>>

}