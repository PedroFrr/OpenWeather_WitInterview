package com.pedrofr.androidchallengewit.database.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Coordinate(
    @Json(name = "lon") val longitude: Double,
    @Json(name = "lat") val latitude: Double
)
