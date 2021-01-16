package com.pedrofr.androidchallengewit.utils

//Converts Kelvin to Celsius
//Just need to subtract 273.15 from Kelvin to obtain the respective Celsius value
//Rounds the result to two decimal places
fun Double.kelvinToCelsius(): String{
    val result = this.minus(273.15)
    val twoDecimalsResult = String.format("%.2f", result)
    return  "${twoDecimalsResult}º"
}