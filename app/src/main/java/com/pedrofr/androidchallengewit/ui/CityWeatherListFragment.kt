package com.pedrofr.androidchallengewit.ui

import android.Manifest
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
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
    private val citiesAdapter by lazy { CityListAdapter(::navigateToCityWeatherDetail) }

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

        binding.currentLocationCardView.setOnClickListener {
            requestPermission.launch(Manifest.permission.ACCESS_FINE_LOCATION) //ask for user permissions runtime in order to access his position and then get his current location weather
        }

    }

    private fun initObservables() {
        cityWeatherListViewModel.getCitiesByName().observe(viewLifecycleOwner) { cities ->
            citiesAdapter.submitList(cities)
        }
    }

    //New onActivityResult API. Easier permission management
    private val requestPermission =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                view?.findNavController()?.navigate(R.id.cityListToWeatherDetail)
                Log.d("DEBUG", "permission granted")
            } else {
                Log.d("DEBUG", "permission denied")
            }
        }

    private fun navigateToCityWeatherDetail(view: View, cityId: Long){
        val direction = CityWeatherListFragmentDirections.cityListToWeatherDetail(cityId)
        view.findNavController().navigate(direction)
    }

}