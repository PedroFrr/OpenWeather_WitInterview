package com.pedrofr.androidchallengewit.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.pedrofr.androidchallengewit.database.model.City
import com.pedrofr.androidchallengewit.database.model.Weather

@Dao
interface WeatherDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWeatherDetail(weather: Weather)


}
