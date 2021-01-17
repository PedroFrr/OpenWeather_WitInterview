package com.pedrofr.androidchallengewit.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Entity(tableName = "cities")
@JsonClass(generateAdapter = true)
data class City(
    @PrimaryKey @Json(name = "id") val id: Long,
    @Json(name = "name") val name: String
)
