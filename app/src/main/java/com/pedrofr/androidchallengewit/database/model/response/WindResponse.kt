package com.pedrofr.androidchallengewit.database.model.response


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class WindResponse(
    @Json(name = "deg") val deg: Int,
    @Json(name = "speed") val speed: Double
)