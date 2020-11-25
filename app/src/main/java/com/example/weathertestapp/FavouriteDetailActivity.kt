package com.example.weathertestapp

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.weathertestapp.utils.Constants
import com.example.weathertestapp.viewmodels.FavouriteCityWeatherViewModel
import com.example.weathertestapp.viewmodels.FavouriteCityWeatherViewModelFactory

private val LOG_TAG = FavouriteDetailActivity::class.java.simpleName

class FavouriteDetailActivity : AppCompatActivity() {

    private val favouriteCityWeatherViewModel: FavouriteCityWeatherViewModel by viewModels {
        FavouriteCityWeatherViewModelFactory((application as WeatherApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favourite_detail)

        supportActionBar?.title = "Detailed city weather"

        val cityId = intent.getIntExtra(Constants.CITY_ID, 0)

        favouriteCityWeatherViewModel.getCityById(cityId).observe(this, Observer {
            Log.e(LOG_TAG, it.toString())
        })
    }
}