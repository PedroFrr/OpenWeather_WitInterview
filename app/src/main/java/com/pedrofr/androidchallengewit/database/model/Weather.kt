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
    val actualTemperature: Double,
    val minTemperature: Double,
    val maxTemperature: Double,
    val humidity: Int
)
