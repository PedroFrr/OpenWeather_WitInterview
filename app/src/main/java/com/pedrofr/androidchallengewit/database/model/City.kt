package com.pedrofr.androidchallengewit.database.model

import androidx.room.Entity
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Entity(tableName = "cities")
@JsonClass(generateAdapter = true)
data class City(
    @Json(name = "id") val id: Long,
    @Json(name = "name") val name: String,
    @Json(name = "state") val state: String,
    @Json(name = "country") val country: String,
    @Json(name = "coord") val coordinate: List<Coordinate>
)
