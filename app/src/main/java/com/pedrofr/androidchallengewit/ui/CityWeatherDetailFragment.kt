package com.pedrofr.androidchallengewit.ui

import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Looper
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.google.android.gms.location.*
import com.pedrofr.androidchallengewit.R
import com.pedrofr.androidchallengewit.database.model.Failure
import com.pedrofr.androidchallengewit.database.model.Loading
import com.pedrofr.androidchallengewit.database.model.Success
import com.pedrofr.androidchallengewit.databinding.FragmentCityWeatherDetailBinding
import com.pedrofr.androidchallengewit.utils.*
import com.pedrofr.androidchallengewit.viewmodels.CityWeatherDetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import kotlin.math.min

@AndroidEntryPoint
class CityWeatherDetailFragment : Fragment(R.layout.fragment_city_weather_detail) {

    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var locationRequest: LocationRequest
    private lateinit var locationCallback: LocationCallback

    private val binding by viewBinding(FragmentCityWeatherDetailBinding::bind)
    private val cityWeatherDetailViewModel by viewModels<CityWeatherDetailViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())

        initUi()
        initObservables()
    }

    private fun initUi() {
        //If arguments are null it means the user clicked on current location
        //This solution might not be the best but I just wanted to reuse the detail layout and fragment for both actions (where users selects a specific city or by location)
        arguments?.let {
            val args = CityWeatherDetailFragmentArgs.fromBundle(it)
            val cityId = args.cityId
            if(cityId  == 0L){
                setLocationRequest()
                initLocation()
                startLocationUpdates()
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
                    binding.errorGroup.gone()
                    binding.weatherDetailCardView.gone()
                    binding.additionalInfo.gone()
                }
                is Success -> {
                    with(result.data){
                        binding.loadingProgressBar.gone()
                        binding.cityName.text = cityName
                        binding.temperature.text = getString(R.string.placeholder_temperature, actualTemperature)
                        binding.weatherDescription.text = description
                        binding.humidityLevel.text = getString(R.string.placeholder_percentage, humidity)
                        binding.currentDayOfMonth.text = timestamp.unixTimestampToDayOfMonth()
                        binding.currentDayOfWeek.text = timestamp.unixTimestampToDayOfWeek()
                        binding.minTemperatureValue.text = getString(R.string.placeholder_temperature, minTemperature)
                        binding.maxTemperatureValue.text = getString(R.string.placeholder_temperature, maxTemperature)

                        //Load image with Glide
                        Glide.with(this@CityWeatherDetailFragment)
                            .load("$OPEN_WEATHER_ICON_URL${icon}.png")
                            .centerCrop()
                            .placeholder(R.drawable.ic_baseline_wb_cloudy_24)
                            .fallback(R.drawable.ic_baseline_wb_cloudy_24)
                            .into(binding.weatherIcon)
                    }

                    binding.weatherDetailCardView.visible()
                    binding.additionalInfo.visible()

                }
                is Failure -> {
                    binding.loadingProgressBar.gone()
                    binding.weatherDetailCardView.gone()
                    binding.additionalInfo.gone()
                    binding.errorGroup.visible()
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

    private fun startLocationUpdates() {
        if (context?.let { ActivityCompat.checkSelfPermission(it, android.Manifest.permission.ACCESS_FINE_LOCATION) } == PackageManager.PERMISSION_GRANTED
        ) {
            fusedLocationClient.requestLocationUpdates(
                locationRequest,
                locationCallback,
                Looper.getMainLooper()
            )
        }
    }



    private fun stopLocationUpdates() {
        fusedLocationClient.removeLocationUpdates(locationCallback)
    }

    private fun setLocationRequest() {
        locationRequest = LocationRequest.create().apply {
            interval = 10000
            fastestInterval = 5000
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        }
    }


}