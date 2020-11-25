package com.example.weathertestapp.adpaters

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.weathertestapp.R
import com.example.weathertestapp.databinding.RecyclerFavouritesBinding
import com.example.weathertestapp.favouritefeature.CITY_ID
import com.example.weathertestapp.favouritefeature.FavouriteDetailActivity
import com.example.weathertestapp.models.db.CityWeather


class FavouriteCityWeatherAdapter(private val cityWeather: List<CityWeather>) :
    RecyclerView.Adapter<FavouriteCityWeatherAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            RecyclerFavouritesBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val weatherCity = cityWeather[position]
        holder.updateContent(weatherCity)
    }

    override fun getItemCount() = cityWeather.size

    inner class ViewHolder(private val recyclerFavouritesBinding: RecyclerFavouritesBinding) :
        RecyclerView.ViewHolder(recyclerFavouritesBinding.root) {

        private var cityWeather: CityWeather? = null

        fun updateContent(cityWeather: CityWeather) {
            this.cityWeather = cityWeather
            recyclerFavouritesBinding.cityNameTextView.text =
                recyclerFavouritesBinding.root.resources.getString(
                    R.string.city_name_text,
                    cityWeather.name
                )
            recyclerFavouritesBinding.countryTextView.text =
                recyclerFavouritesBinding.root.resources.getString(
                    R.string.country_name_text,
                    cityWeather.country
                )
            recyclerFavouritesBinding.latTextView.text =
                recyclerFavouritesBinding.root.resources.getString(
                    R.string.latitude_text,
                    cityWeather.latitude
                )
            recyclerFavouritesBinding.longTextView.text =
                recyclerFavouritesBinding.root.resources.getString(
                    R.string.longitude_text,
                    cityWeather.longitude
                )

            itemView.setOnClickListener {
                itemView.context.startActivity(
                    Intent(
                        itemView.context,
                        FavouriteDetailActivity::class.java
                    ).apply {
                        putExtra(CITY_ID, cityWeather.id)
                    })
            }

            if (cityWeather.favourited!!) {
                recyclerFavouritesBinding.favouriteBtn.setImageDrawable(
                    ContextCompat.getDrawable(
                        itemView.context,
                        R.drawable.ic_filled_in_favorite
                    )
                )
            }
        }
    }

}