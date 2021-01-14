package com.pedrofr.androidchallengewit.repository

import com.pedrofr.androidchallengewit.database.dao.CityDao
import com.pedrofr.androidchallengewit.database.model.City
import com.pedrofr.androidchallengewit.networking.OpenWeatherClient
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val cityDao: CityDao,
    private val openWeatherClient: OpenWeatherClient
) : Repository {

    override fun fetchCities(): Flow<List<City>> = cityDao.fetchCities()

    //TODO DONT forget to write the info on the DB after obtaining it (Offline first!!)
}