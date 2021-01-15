package com.pedrofr.androidchallengewit.networking.mapper

import com.pedrofr.androidchallengewit.database.model.Weather
import com.pedrofr.androidchallengewit.database.model.response.GetWeatherResponse

interface ApiMapper {

    fun mapApiWeatherToDomain(apiWeather: GetWeatherResponse): Weather
}
