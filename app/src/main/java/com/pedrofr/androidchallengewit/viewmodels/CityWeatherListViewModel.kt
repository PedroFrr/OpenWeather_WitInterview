package com.pedrofr.androidchallengewit.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.pedrofr.androidchallengewit.database.model.City
import com.pedrofr.androidchallengewit.repository.Repository
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class CityWeatherListViewModel @ViewModelInject constructor(
    private val repository: Repository
) : ViewModel() {

    private val debouncePeriod: Long = 250
    private var searchJob: Job? = null
    private var _searchCitiesLiveData: LiveData<List<City>>
    fun getCitiesByName() = _searchCitiesLiveData
    private val _searchFieldTextLiveData = MutableLiveData("")


    init {

        //every time the search field changes we re-execute the query
        _searchCitiesLiveData = _searchFieldTextLiveData.switchMap {
            fetchCitiesByName(it)
        }

    }

    fun onSearchQuery(query: String) {
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(debouncePeriod)
            _searchFieldTextLiveData.value = query
        }
    }

    private fun fetchCitiesByName(query: String): LiveData<List<City>> {
        val liveData = MutableLiveData<List<City>>()
        viewModelScope.launch {
            if (query.isBlank()) {
                repository.fetchCities()
                    .collect {
                        liveData.postValue(it)
                    }
            }else{
                repository.fetchCitiesByName(query)
                    .collect {
                        liveData.postValue(it)
                    }
            }
        }
        return liveData
    }


}