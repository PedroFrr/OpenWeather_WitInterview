package com.pedrofr.androidchallengewit.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "weather")
data class Weather(
    @PrimaryKey val id: String = UUID.randomUUID().toString(),
    val main: String,
    val description: String,
    val icon: String,
    val actualTemperature: Int,
    val minTemperature: Int,
    val maxTemperature: Int,
    val humidity: Int,
    val cityId: Long,
    val cityName: String,
    val timestamp: Long
)
