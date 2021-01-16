package com.pedrofr.androidchallengewit.networking.mapper

import com.pedrofr.androidchallengewit.database.model.Weather
import com.pedrofr.androidchallengewit.database.model.response.GetWeatherResponse
import com.pedrofr.androidchallengewit.database.model.response.WeatherResponse
import javax.inject.Inject

class ApiMapperImpl @Inject constructor() : ApiMapper {

    //TODO refactor the List<WeatherResponse> mapping
    override fun mapApiWeatherToDomain(apiWeather: GetWeatherResponse): Weather = with(apiWeather) {
        Weather(
            main = weatherResponse.first().main,
            description = weatherResponse.first().description,
            icon = weatherResponse.first().icon,
            actualTemperature = mainResponse.temp,
            minTemperature = mainResponse.tempMin,
            maxTemperature = mainResponse.tempMax,
            humidity = mainResponse.humidity
        )
    }

    private fun mapApiWeatherToDb(apiWeatherResponse: WeatherResponse) = with(apiWeatherResponse) {

    }

}