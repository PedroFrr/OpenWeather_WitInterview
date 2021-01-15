package com.pedrofr.androidchallengewit.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.pedrofr.androidchallengewit.database.model.City
import com.pedrofr.androidchallengewit.databinding.ItemCityBinding

class CityListAdapter() : ListAdapter<City, CityListAdapter.ViewHolder>(CityListDiffCallBack()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    //TODO add view binding
    class ViewHolder private constructor(
        private val binding: ItemCityBinding
     ) : RecyclerView.ViewHolder(binding.root){
        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemCityBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }

        //TODO maybe explore if it's need to change this reference operator here
        fun bind(item: City){
            with(binding) {
                cityName.text = item.name
                cityInfo.setOnClickListener { view ->
                    navigateCityWeatherDetail(view, item.id)
                }
            }

        }

        //TODO validate if I should passe a function here or use it directly
        private fun navigateCityWeatherDetail(view: View, cityId: Long){
            val direction = CityWeatherListFragmentDirections.cityListToWeatherDetail(cityId)
            view.findNavController().navigate(direction)
        }
    }
}

private class CityListDiffCallBack: DiffUtil.ItemCallback<City>() {
    override fun areContentsTheSame(oldItem: City, newItem: City): Boolean = oldItem.id == newItem.id

    override fun areItemsTheSame(oldItem: City, newItem: City): Boolean = oldItem == newItem
}