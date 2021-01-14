package com.pedrofr.androidchallengewit.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pedrofr.androidchallengewit.database.model.City
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi

class CityWeatherListViewModel: ViewModel() {

    private val cities = MutableLiveData<List<City>>()

    fun fetchCities() {
        val json = context.
        val citiesJson = resources.openRawResource(R.raw.players).bufferedReader().use {
            it.readText()
        }
        val moshi: Moshi = Moshi.Builder().build()
        val adapter: JsonAdapter<City> = moshi.adapter(City::class.java)
        val city = adapter.fromJson(moviesJson))

    }


}