package com.pedrofr.androidchallengewit.ui

import android.os.Bundle
import android.view.View
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.pedrofr.androidchallengewit.R
import com.pedrofr.androidchallengewit.databinding.FragmentCityListBinding
import com.pedrofr.androidchallengewit.utils.viewBinding
import com.pedrofr.androidchallengewit.viewmodels.CityWeatherListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CityWeatherListFragment : Fragment(R.layout.fragment_city_list) {

    private val binding by viewBinding(FragmentCityListBinding::bind)
    private val cityWeatherListViewModel by viewModels<CityWeatherListViewModel>()
    private val citiesAdapter by lazy { CityListAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUi()
        initObservables()
    }

    private fun initUi() {

        binding.citiesRecyclerView.apply {
            adapter = citiesAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
            hasFixedSize()
        }

        /*KTX Core addTextChangedListener
        * Once search changes (and after debounce period) display new data
         */
        binding.searchEditText.doOnTextChanged { text, _, _, _ ->
            cityWeatherListViewModel.onSearchQuery(text.toString())
        }

    }

    private fun initObservables() {
        cityWeatherListViewModel.getCitiesByName().observe(viewLifecycleOwner) { cities ->
            citiesAdapter.submitList(cities)
        }

        cityWeatherListViewModel.citiesMediatorLiveData.observe(viewLifecycleOwner) { cities ->
            citiesAdapter.submitList(cities)
        }
    }


}