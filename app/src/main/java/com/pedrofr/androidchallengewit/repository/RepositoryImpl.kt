package com.pedrofr.androidchallengewit.repository

import com.pedrofr.androidchallengewit.database.dao.CityDao
import com.pedrofr.androidchallengewit.database.model.*
import com.pedrofr.androidchallengewit.networking.OpenWeatherClient
import com.pedrofr.androidchallengewit.networking.mapper.ApiMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val cityDao: CityDao,
    private val openWeatherClient: OpenWeatherClient,
    private val apiMapper: ApiMapper
) : Repository {

    override fun fetchCities(): Flow<List<City>> = cityDao.fetchCities()

    override suspend fun fetchCityInfo(cityId: Long): City = cityDao.fetchCity(cityId)

    override suspend fun fetchCityCurrentWeather(cityId: Long): Flow<Result<Weather>> {
        return flow {
            emit(Loading)
            val results = openWeatherClient.fetchCityCurrentWeather(cityId)
            if (results is Success) {
                val weatherDomain =
                    apiMapper.mapApiWeatherToDomain(results.data) //Maps API response into Domain response
                emit(Success(weatherDomain))
            } else if (results is Failure) {
                emit(Failure(results.error))
            }
        }
    }

    override suspend fun fetchLocationCurrentWeather(
        latitude: Double,
        longitude: Double
    ): Flow<Result<Weather>> {
        return flow {
            emit(Loading)
            val results = openWeatherClient.fetchLocationCurrentWeather(latitude, longitude)
            if (results is Success) {
                val weatherDomain = apiMapper.mapApiWeatherToDomain(results.data)
                emit(Success(weatherDomain))

            } else if (results is Failure) {
                emit(Failure(results.error))
            }
        }
    }

    override fun fetchCitiesByName(cityName: String): Flow<List<City>> = cityDao.fetchCitiesByName(cityName)

}