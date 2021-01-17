package com.pedrofr.androidchallengewit.ui

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.google.android.gms.location.*
import com.pedrofr.androidchallengewit.R
import com.pedrofr.androidchallengewit.database.model.Failure
import com.pedrofr.androidchallengewit.database.model.Loading
import com.pedrofr.androidchallengewit.database.model.Success
import com.pedrofr.androidchallengewit.databinding.FragmentCityWeatherDetailBinding
import com.pedrofr.androidchallengewit.utils.OPEN_WEATHER_ICON_URL
import com.pedrofr.androidchallengewit.utils.awaitLastLocation
import com.pedrofr.androidchallengewit.utils.createLocationRequest
import com.pedrofr.androidchallengewit.utils.viewBinding
import com.pedrofr.androidchallengewit.viewmodels.CityWeatherDetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import gone
import kotlinx.coroutines.launch
import toast
import visible
import java.util.*

@AndroidEntryPoint
class CityWeatherDetailFragment : Fragment(R.layout.fragment_city_weather_detail) {

    companion object{
        const val TAG = "City Weather Detail"
    }

    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var locationRequest: LocationRequest
    private lateinit var locationCallback: LocationCallback

    private val binding by viewBinding(FragmentCityWeatherDetailBinding::bind)
    private val cityWeatherDetailViewModel by viewModels<CityWeatherDetailViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity()) //TODO refactor

        initUi()
        initObservables()
    }

    private fun initUi() {
        //If arguments are null it means the user clicked on current location
        //TODO if there is time think of a better solution
        arguments?.let {
            val args = CityWeatherDetailFragmentArgs.fromBundle(it)
            val cityId = args.cityId
            if(cityId  == 0L){

                lifecycleScope.launch {
                    getLastKnownLocation()
                }
                initLocation()
                startUpdatingLocation()
            }else{
                cityWeatherDetailViewModel.fetchCityCurrentWeather(cityId)
            }

        }
    }

    private fun initObservables() {

        cityWeatherDetailViewModel.getWeather().observe(viewLifecycleOwner) { result ->
            when (result) {
                is Loading -> {
                    binding.loadingProgressBar.visible()
                    binding.weatherDetailCardView.gone()
                }
                is Success -> {
                    with(result.data){
                        binding.loadingProgressBar.gone()
                        binding.cityName.text = cityName
                        binding.temperature.text = "${actualTemperature}º"//TODO refactor
                        binding.weatherDescription.text = description
                        binding.humidityLevel.text = "${humidity}%" //TODO refactor
                        binding.currentDay.text = "30" //TODO replace with current day

                        //Load image with Glide
                        Glide.with(this@CityWeatherDetailFragment)
                            .load("$OPEN_WEATHER_ICON_URL${icon}.png")
                            .centerCrop()
                            .placeholder(R.drawable.ic_baseline_wb_cloudy_24)
                            .fallback(R.drawable.ic_baseline_wb_cloudy_24)
                            .into(binding.weatherIcon)
                    }

                    binding.weatherDetailCardView.visible()

                }
                is Failure -> {
                    //TODO Failure status
                    toast("Failleed")
                }
            }
        }
    }

    private fun initLocation(){

        locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult?) {
                locationResult ?: return
                val latitude = locationResult.lastLocation.latitude
                val longitude = locationResult.lastLocation.longitude
                cityWeatherDetailViewModel.fetchLocationCurrentWeather(latitude, longitude)
                //After retrieving current location stop location updates as we don't need to keep track of it
                stopLocationUpdates()

            }
        }
    }

    @SuppressLint("MissingPermission")
    private fun startUpdatingLocation() {
        fusedLocationClient.requestLocationUpdates(
            createLocationRequest(),
            locationCallback,
            Looper.getMainLooper()
        ).addOnFailureListener { e ->
            toast("Unable to get location.")
            Log.d(TAG, "Unable to get location", e)
        }
    }

    private suspend fun getLastKnownLocation() {
        try {
            val lastLocation = fusedLocationClient.awaitLastLocation()
            val latitude = lastLocation.latitude
            val longitude = lastLocation.longitude
            cityWeatherDetailViewModel.fetchLocationCurrentWeather(latitude, longitude)
        } catch (e: Exception) {
            toast("Unable to get location.")
            Log.d(TAG, "Unable to get location", e)
        }
    }

    private fun stopLocationUpdates() {
        fusedLocationClient.removeLocationUpdates(locationCallback)
    }

}