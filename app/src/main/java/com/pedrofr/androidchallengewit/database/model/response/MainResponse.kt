package com.pedrofr.androidchallengewit.database.model.response


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MainResponse(
    @Json(name = "humidity") val humidity: Int,
    @Json(name = "temp") val temp: Double,
    @Json(name = "temp_max") val tempMax: Double,
    @Json(name = "temp_min") val tempMin: Double
)