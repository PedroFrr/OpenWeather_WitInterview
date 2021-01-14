package com.pedrofr.androidchallengewit.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Entity(tableName = "cities")
@JsonClass(generateAdapter = true)
data class City(
    @PrimaryKey @Json(name = "id") val id: Long,
    @Json(name = "name") val name: String,
    @Json(name = "state") val state: String,
    @Json(name = "country") val country: String,
//    @Json(name = "coord") val coordinate: List<Coordinate>,
    //TODO I removed this part in order to not have to do a custom adapter. Can get back to this if there's time
    @Json(name = "lon") val longitude: Double,
    @Json(name = "lat") val latitude: Double
)
