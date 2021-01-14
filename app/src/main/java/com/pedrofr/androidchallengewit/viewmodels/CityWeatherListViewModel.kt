package com.pedrofr.androidchallengewit.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import com.pedrofr.androidchallengewit.database.model.City
import com.pedrofr.androidchallengewit.repository.Repository
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi

class CityWeatherListViewModel @ViewModelInject constructor(
    private val repository: Repository
): ViewModel() {

    private val cities = MutableLiveData<List<City>>()

    //TODO see if we need to call viewModelScope here. For now I'll just remove the suspend call
    fun fetchCities() = repository.fetchCities().asLiveData()


}