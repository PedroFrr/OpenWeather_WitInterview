package com.pedrofr.androidchallengewit.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pedrofr.androidchallengewit.database.model.Result
import com.pedrofr.androidchallengewit.database.model.Weather
import com.pedrofr.androidchallengewit.repository.Repository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class CityWeatherDetailViewModel @ViewModelInject constructor(
    private val repository: Repository
) : ViewModel() {


    private val _weather = MutableLiveData<Result<Weather>>()
    fun getWeather(): LiveData<Result<Weather>> = _weather

    fun fetchCityCurrentWeather(cityId: Long) {
        viewModelScope.launch {
            repository.fetchCityCurrentWeather(cityId)
                .collect {
                    _weather.postValue(it)
                }
        }
    }

    fun fetchLocationCurrentWeather(latitude: Double, longitude: Double){
        viewModelScope.launch {
            repository.fetchLocationCurrentWeather(latitude, longitude)
                .collect {
                    _weather.postValue(it)
                }
        }
    }


}