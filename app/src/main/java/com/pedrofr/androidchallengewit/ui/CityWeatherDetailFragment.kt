package com.pedrofr.androidchallengewit.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.pedrofr.androidchallengewit.R
import com.pedrofr.androidchallengewit.database.model.Failure
import com.pedrofr.androidchallengewit.database.model.Loading
import com.pedrofr.androidchallengewit.database.model.Success
import com.pedrofr.androidchallengewit.databinding.FragmentCityWeatherDetailBinding
import com.pedrofr.androidchallengewit.utils.viewBinding
import com.pedrofr.androidchallengewit.viewmodels.CityWeatherDetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import toast

@AndroidEntryPoint
class CityWeatherDetailFragment : Fragment(R.layout.fragment_city_weather_detail) {

    private val binding by viewBinding(FragmentCityWeatherDetailBinding::bind)
    private val cityWeatherDetailViewModel by viewModels<CityWeatherDetailViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        cityWeatherDetailViewModel.fetchLocationCurrentWeather(35.0, -139.0) //TODO Delete
        initUi()
        initObservables()
    }

    private fun initUi() {
        arguments?.let {
            val args = CityWeatherDetailFragmentArgs.fromBundle(it)
            val cityId = args.cityId
            cityWeatherDetailViewModel.fetchCityCurrentWeather(cityId)
        }
    }

    private fun initObservables() {
        cityWeatherDetailViewModel.getCity().observe(viewLifecycleOwner) { city ->
            toast("THis is ${city.name}")
        }

        cityWeatherDetailViewModel.getWeather().observe(viewLifecycleOwner) { result ->
            when (result) {
                is Loading -> {
                    //TODO Loading status
                    toast("LOADINGGGGG")
                }
                is Success -> toast("THis is ${result.data.mainResponse.temp}")
                is Failure -> {
                    //TODO Failure status
                    toast("Failleed")
                }
            }

        }

        //TODO DELETE.....
//        cityWeatherDetailViewModel.getWeatherLocation().observe(viewLifecycleOwner) { result ->
//            when (result) {
//                is Loading -> {
//                    //TODO Loading status
//                    toast("LOADINGGGGG")
//                }
//                is Success -> toast("THis is ${result.data.mainResponse.temp}")
//                is Failure -> {
//                    //TODO Failure status
//                    toast("Failleed")
//                }
//            }
//
//        }

    }


}