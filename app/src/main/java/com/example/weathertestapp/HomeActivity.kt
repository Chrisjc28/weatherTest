package com.example.weathertestapp

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.weathertestapp.viewmodels.FavouriteCityWeatherViewModel
import com.example.weathertestapp.viewmodels.FavouriteCityWeatherViewModelFactory
import com.google.android.material.bottomnavigation.BottomNavigationView


private val LOG_TAG = HomeActivity::class.java.simpleName

class HomeActivity : AppCompatActivity() {

    private val bottomNavigationView: BottomNavigationView by lazy {
        findViewById<BottomNavigationView>(R.id.bottom_navigation)
    }

    private val favouriteCityWeatherViewModel: FavouriteCityWeatherViewModel by viewModels {
        FavouriteCityWeatherViewModelFactory((application as WeatherApplication).repository)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.title = "Home screen"

        setUpBottomNav()

        favouriteCityWeatherViewModel.allCitiesWeather.observe(this, Observer {
            if (it.isNotEmpty()) {
                setUpFavouriteFragment()
                bottomNavigationView.selectedItemId = R.id.favourite_btn
            } else {
                setUpSearchFragment()
                bottomNavigationView.selectedItemId = R.id.search_btn
            }
        })

    }

    private fun setUpSearchFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, SearchFragment.newInstance()).commit()
    }


    private fun setUpFavouriteFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, FavouriteFragment.newInstance()).commit()
    }

    private fun setUpBottomNav() {
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.search_btn -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.container, SearchFragment.newInstance()).commit()
                }
                R.id.favourite_btn -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.container, FavouriteFragment.newInstance()).commit()
                }
            }
            true
        }
    }
}