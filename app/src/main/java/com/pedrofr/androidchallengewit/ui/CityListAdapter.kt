package com.pedrofr.androidchallengewit.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.pedrofr.androidchallengewit.database.model.City
import com.pedrofr.androidchallengewit.databinding.ListItemCityBinding

class CityListAdapter(private val navigateToCityWeatherDetail: (view: View, cityId: Long) -> Unit ) : ListAdapter<City, CityListAdapter.ViewHolder>(CityListDiffCallBack()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, navigateToCityWeatherDetail)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(
        private val binding: ListItemCityBinding
     ) : RecyclerView.ViewHolder(binding.root){
        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemCityBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }

        fun bind(item: City,
                 navigateToCityWeatherDetail: (view: View, cityId: Long) -> Unit){
            with(binding) {
                cityName.text = item.name
                cityInfo.setOnClickListener { view ->
                    navigateToCityWeatherDetail(view, item.id)
                }
            }

        }

    }
}

private class CityListDiffCallBack: DiffUtil.ItemCallback<City>() {
    override fun areContentsTheSame(oldItem: City, newItem: City): Boolean = oldItem.id == newItem.id

    override fun areItemsTheSame(oldItem: City, newItem: City): Boolean = oldItem == newItem
}