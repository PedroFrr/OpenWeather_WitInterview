package com.pedrofr.androidchallengewit.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pedrofr.androidchallengewit.database.model.City
import com.pedrofr.androidchallengewit.database.model.Result
import com.pedrofr.androidchallengewit.database.model.response.GetWeatherResponse
import com.pedrofr.androidchallengewit.repository.Repository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class CityWeatherDetailViewModel @ViewModelInject constructor(
    private val repository: Repository
) : ViewModel() {

    private val _city = MutableLiveData<City>()
    fun getCity(): LiveData<City> = _city

    private val _weather = MutableLiveData<Result<GetWeatherResponse>>()
    fun getWeather(): LiveData<Result<GetWeatherResponse>> = _weather

//    private val _weatherLocation = MutableLiveData<Result<GetWeatherResponse>>()
//    fun getWeatherLocation(): LiveData<Result<GetWeatherResponse>> = _weatherLocation

    fun fetchCityCurrentWeather(cityId: Long) {
        viewModelScope.launch {
            repository.fetchCityCurrentWeather(cityId)
                .collect {
                    _weather.postValue(it)
                }
        }
    }

    //TODO Deleteeee
//    fun fetchLocationCurrentWeather(latitude: Double, longitude: Double){
//        viewModelScope.launch {
//            repository.fetchLocationCurrentWeather(latitude, longitude)
//                .collect {
//                    _weatherLocation.postValue(it)
//                }
//        }
//    }


}