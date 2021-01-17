package com.pedrofr.androidchallengewit.networking.mapper

import com.pedrofr.androidchallengewit.database.model.Weather
import com.pedrofr.androidchallengewit.database.model.response.GetWeatherResponse
import javax.inject.Inject

class ApiMapperImpl @Inject constructor() : ApiMapper {

    //TODO refactor the List<WeatherResponse> mapping
    override fun mapApiWeatherToDomain(apiWeather: GetWeatherResponse): Weather = with(apiWeather) {
        Weather(
            main = weatherResponse.first().main,
            description = weatherResponse.first().description,
            icon = weatherResponse.first().icon,
            actualTemperature = mainResponse.temp.toInt(), //Conversion to int as we don't want to show decimals
            minTemperature = mainResponse.tempMin.toInt(),
            maxTemperature = mainResponse.tempMax.toInt(),
            humidity = mainResponse.humidity,
            cityId = cityId,
            cityName = cityName,
            timestamp = timestamp
        )
    }

}