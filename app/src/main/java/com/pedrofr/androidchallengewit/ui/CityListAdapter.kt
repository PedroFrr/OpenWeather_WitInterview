package com.pedrofr.androidchallengewit.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.pedrofr.androidchallengewit.database.model.City
import com.pedrofr.androidchallengewit.databinding.FragmentCityListBinding
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
    class ViewHolder private constructor(private val binding: ItemCityBinding) : RecyclerView.ViewHolder(binding.root){
        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemCityBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }

        fun bind(item: City){
            //TODO set actions for each layout attribute, including the onClickListener
//            with(itemView) {
//                binding.citiesRecyclerView.
//            }
            binding.cityName.text = item.name

        }
    }
}

private class CityListDiffCallBack: DiffUtil.ItemCallback<City>() {
    override fun areContentsTheSame(oldItem: City, newItem: City): Boolean = oldItem.id == newItem.id

    override fun areItemsTheSame(oldItem: City, newItem: City): Boolean = oldItem == newItem
}