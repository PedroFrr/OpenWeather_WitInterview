package com.pedrofr.androidchallengewit.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "weather")
data class Weather(
    @PrimaryKey val id: String = UUID.randomUUID().toString(),
    val temperature: Int)
