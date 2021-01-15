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

    //TODO see how to refactor this
    fun fetchCity(cityId: Long) {
        viewModelScope.launch {
            val result = repository.fetchCity(cityId)
            _city.value = result
        }
    }

    fun fetchCityCurrentWeather(cityId: Long) {
        viewModelScope.launch {
            repository.fetchCityCurrentWeather(cityId)
                .collect {
                    _weather.postValue(it)
                }
        }
    }


}