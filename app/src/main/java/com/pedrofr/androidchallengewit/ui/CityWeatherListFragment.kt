package com.pedrofr.androidchallengewit.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.pedrofr.androidchallengewit.R
import com.pedrofr.androidchallengewit.databinding.FragmentCityListBinding
import com.pedrofr.androidchallengewit.utils.viewBinding
import com.pedrofr.androidchallengewit.viewmodels.CityWeatherListViewModel

class CityWeatherListFragment : Fragment(R.layout.fragment_city_list) {

    private val binding by viewBinding(FragmentCityListBinding::bind)

    private val cityWeatherListViewModel by viewModels<CityWeatherListViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initObservables()
    }

    private fun initObservables(){
        cityWeatherListViewModel.fetchCities().observe(viewLifecycleOwner) {
            //TODO submit list to adapter
        }
    }




}