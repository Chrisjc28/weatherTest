package com.example.weathertestapp.adpaters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.weathertestapp.FavouriteDetailActivity
import com.example.weathertestapp.R
import com.example.weathertestapp.models.db.CityWeather
import com.example.weathertestapp.respositories.FavouriteWeatherRepository
import com.example.weathertestapp.respositories.FavouriteWeatherRepositoryImpl
import com.example.weathertestapp.utils.Constants
import com.example.weathertestapp.viewmodels.FavouriteCityWeatherViewModel
import kotlinx.android.synthetic.main.recycler_favourites.view.*
import kotlinx.android.synthetic.main.recycler_item_add_favourite.view.*
import kotlinx.android.synthetic.main.recycler_item_add_favourite.view.cityNameTextView
import kotlinx.android.synthetic.main.recycler_item_add_favourite.view.countryTextView
import kotlinx.android.synthetic.main.recycler_item_add_favourite.view.favourite_btn
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch


class FavouriteCityWeatherAdapter(private val cityWeather: List<CityWeather>) : RecyclerView.Adapter<FavouriteCityWeatherAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.recycler_favourites, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val weatherCity = cityWeather[position]
        holder.updateContent(weatherCity)
    }

    override fun getItemCount(): Int {
        return cityWeather.size
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private var cityWeather: CityWeather? = null

        fun updateContent(cityWeather: CityWeather) {
            this.cityWeather = cityWeather
            itemView.cityNameTextView!!.text =  "City: ${cityWeather.name}"
            itemView.countryTextView!!.text =  "Country:  ${cityWeather.country}"
            itemView.latTextView!!.text =  "Latitude: ${cityWeather.latitude}"
            itemView.longTextView!!.text =  "Longitude: ${cityWeather.longitude}"

            itemView.setOnClickListener {
                itemView.context.startActivity(Intent(itemView.context, FavouriteDetailActivity::class.java).apply {
                    putExtra(Constants.CITY_ID, cityWeather.id)
                })
            }

            if (cityWeather.favourited!!) {
                itemView.favourite_btn.setImageDrawable(ContextCompat.getDrawable(itemView.context, R.drawable.ic_filled_in_favorite))
            }
        }
    }

}