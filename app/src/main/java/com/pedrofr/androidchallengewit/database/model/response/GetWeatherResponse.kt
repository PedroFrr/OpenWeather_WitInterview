package com.pedrofr.androidchallengewit.database.model.response


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GetWeatherResponse(
    @Json(name = "main") val mainResponse: MainResponse,
    @Json(name = "weather") val weatherResponse: List<WeatherResponse>,
    @Json(name = "wind") val windResponse: WindResponse,
    @Json(name = "id") val cityId: Long,
    @Json(name = "name") val cityName: String,
    @Json(name = "dt") val timestamp: Long
)