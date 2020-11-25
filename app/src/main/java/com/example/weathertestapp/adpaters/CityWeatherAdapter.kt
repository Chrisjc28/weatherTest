package com.example.weathertestapp.adpaters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.weathertestapp.R
import com.example.weathertestapp.databinding.RecyclerItemAddFavouriteBinding
import com.example.weathertestapp.models.db.CityWeather
import com.example.weathertestapp.viewmodels.FavouriteCityWeatherViewModel

class CityWeatherAdapter(
    private val cityWeather: List<CityWeather>,
    private val favouriteCityWeatherViewModel: FavouriteCityWeatherViewModel
) : RecyclerView.Adapter<CityWeatherAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            RecyclerItemAddFavouriteBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), favouriteCityWeatherViewModel
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val weatherCity = cityWeather[position]
        holder.updateContent(weatherCity)
        holder.setUpFavouriteClick()
    }

    override fun getItemCount() = cityWeather.size

    inner class ViewHolder(
        private val recyclerItemAddFavouriteBinding: RecyclerItemAddFavouriteBinding,
        private val favouriteCityWeatherViewModel: FavouriteCityWeatherViewModel
    ) : RecyclerView.ViewHolder(recyclerItemAddFavouriteBinding.root) {

        private var cityWeather: CityWeather? = null

        fun updateContent(cityWeather: CityWeather) {
            this.cityWeather = cityWeather

            recyclerItemAddFavouriteBinding.cityNameTextView.text = "City: ${cityWeather.name}"
            recyclerItemAddFavouriteBinding.countryTextView.text =
                "Country:  ${cityWeather.country}"
        }

        fun setUpFavouriteClick() {
            recyclerItemAddFavouriteBinding.favouriteBtn.setOnClickListener {
                favouriteCityWeatherViewModel.insert(cityWeather!!.copy(favourited = true))
                recyclerItemAddFavouriteBinding.favouriteBtn.setImageDrawable(
                    ContextCompat.getDrawable(
                        itemView.context,
                        R.drawable.ic_filled_in_favorite
                    )
                )
                Toast.makeText(
                    itemView.context,
                    "City has now been marked as a favourite",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }
}