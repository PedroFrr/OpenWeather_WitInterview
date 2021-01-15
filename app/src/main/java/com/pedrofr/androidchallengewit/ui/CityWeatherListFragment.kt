package com.pedrofr.androidchallengewit.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.pedrofr.androidchallengewit.R
import com.pedrofr.androidchallengewit.databinding.FragmentCityListBinding
import com.pedrofr.androidchallengewit.utils.viewBinding
import com.pedrofr.androidchallengewit.viewmodels.CityWeatherListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CityWeatherListFragment : Fragment(R.layout.fragment_city_list) {

    private val binding by viewBinding(FragmentCityListBinding::bind)
    private val cityWeatherListViewModel by viewModels<CityWeatherListViewModel>()
    private val citiesAdapter by lazy { CityListAdapter()}

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUi()
        initObservables()
    }

    private fun initUi() {

        binding.citiesRecyclerView.apply {
            adapter = citiesAdapter
            hasFixedSize()
        }

    }

    private fun initObservables() {
        cityWeatherListViewModel.fetchCities().observe(viewLifecycleOwner) { cities ->
            citiesAdapter.submitList(cities)
        }
    }



}