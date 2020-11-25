package com.example.weathertestapp.adpaters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.weathertestapp.R
import com.example.weathertestapp.models.db.CityWeather
import com.example.weathertestapp.respositories.FavouriteWeatherRepository
import com.example.weathertestapp.respositories.FavouriteWeatherRepositoryImpl
import com.example.weathertestapp.viewmodels.FavouriteCityWeatherViewModel
import kotlinx.android.synthetic.main.recycler_item_add_favourite.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class CityWeatherAdapter(private val cityWeather: List<CityWeather>, private val favouriteCityWeatherViewModel: FavouriteCityWeatherViewModel) : RecyclerView.Adapter<CityWeatherAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.recycler_item_add_favourite, parent, false)
        return ViewHolder(itemView, favouriteCityWeatherViewModel)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val weatherCity = cityWeather[position]
        holder.updateContent(weatherCity)
        holder.setUpFavouriteClick()
    }

    override fun getItemCount(): Int {
        return cityWeather.size
    }


    class ViewHolder(itemView: View, private val favouriteCityWeatherViewModel: FavouriteCityWeatherViewModel) : RecyclerView.ViewHolder(itemView) {

        private var cityWeather: CityWeather? = null

        fun updateContent(cityWeather: CityWeather) {
            this.cityWeather = cityWeather
            itemView.cityNameTextView!!.text =  "City: ${cityWeather.name}"
            itemView.countryTextView!!.text =  "Country:  ${cityWeather.country}"
        }

        fun setUpFavouriteClick() {
            itemView.favourite_btn.setOnClickListener {
                favouriteCityWeatherViewModel.insert(cityWeather!!.copy(favourited = true))
                itemView.favourite_btn.setImageDrawable(ContextCompat.getDrawable(itemView.context, R.drawable.ic_filled_in_favorite))
                Toast.makeText(itemView.context, "City has now been marked as a favourite", Toast.LENGTH_LONG).show()
            }
        }

    }

}