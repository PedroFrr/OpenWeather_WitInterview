package com.pedrofr.androidchallengewit.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.pedrofr.androidchallengewit.database.model.City
import kotlinx.coroutines.flow.Flow

@Dao
interface CityDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(cities: List<City>)

    //TODO for now this is not suspend, refactor to suspend
    @Query("SELECT * FROM cities")
    fun fetchCities(): Flow<List<City>>

    @Query("SELECT * FROM cities WHERE id = :cityId")
    suspend fun fetchCity(cityId: Long): City
}