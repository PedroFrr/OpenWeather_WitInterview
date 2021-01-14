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
    suspend fun insertAll(plants: List<City>)

    @Query("SELECT * FROM cities")
    fun fetchCities(): Flow<List<City>>
}