package com.pedrofr.androidchallengewit.repository

import com.pedrofr.androidchallengewit.database.model.City
import kotlinx.coroutines.flow.Flow

interface Repository {

    fun fetchCities(): Flow<List<City>>
}