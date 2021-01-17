package com.pedrofr.androidchallengewit.database.model.response


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class WeatherResponse(
    @Json(name = "description") val description: String,
    @Json(name = "icon") val icon: String,
    @Json(name = "main") val main: String
)