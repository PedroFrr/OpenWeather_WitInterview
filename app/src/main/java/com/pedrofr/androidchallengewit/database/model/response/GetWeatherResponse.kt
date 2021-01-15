package com.pedrofr.androidchallengewit.database.model.response


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GetWeatherResponse(
    @Json(name = "main") val mainResponse: MainResponse,
    @Json(name = "weather") val weatherResponse: List<WeatherResponse>,
    @Json(name = "wind") val windResponse: WindResponse
)