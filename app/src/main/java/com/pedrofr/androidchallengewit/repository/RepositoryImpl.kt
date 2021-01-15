package com.pedrofr.androidchallengewit.repository

import com.pedrofr.androidchallengewit.database.dao.CityDao
import com.pedrofr.androidchallengewit.database.dao.WeatherDao
import com.pedrofr.androidchallengewit.database.model.*
import com.pedrofr.androidchallengewit.database.model.response.GetWeatherResponse
import com.pedrofr.androidchallengewit.networking.OpenWeatherClient
import com.pedrofr.androidchallengewit.networking.mapper.ApiMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val cityDao: CityDao,
    private val weatherDao: WeatherDao,
    private val openWeatherClient: OpenWeatherClient
) : Repository {

    //TODO DONT forget to write the info on the DB after obtaining it (Offline first!!)
    override fun fetchCities(): Flow<List<City>> = cityDao.fetchCities()

    override suspend fun fetchCity(cityId: Long): City = cityDao.fetchCity(cityId)

    //TODO Refactor see if we should instead use Weather db model
    override suspend fun fetchCityCurrentWeather(cityId: Long): Flow<Result<GetWeatherResponse>> {
        return flow {
            emit(Loading)

            //TODO refactor
            val results = openWeatherClient.fetchCityCurrentWeather(cityId)
            if(results is Success){
                emit(Success(results.data))
            }
        }
    }


}