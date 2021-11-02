package com.example.weathertestapp.home

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.weathertestapp.R
import com.example.weathertestapp.databinding.ActivityHomeBinding
import com.example.weathertestapp.favouritefeature.FavouriteFragment
import com.example.weathertestapp.searchfeature.SearchFragment
import com.example.weathertestapp.viewmodels.FavouriteCityWeatherViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


private val LOG_TAG = HomeActivity::class.java.simpleName

class HomeActivity : AppCompatActivity() {

    private val favouriteCityWeatherViewModel: FavouriteCityWeatherViewModel by viewModel()

    private lateinit var binding: ActivityHomeBinding

    @IdRes
    private var savedState: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        supportActionBar?.title = "Home screen"

        setUpBottomNav()

        if (savedInstanceState != null) {
            binding.bottomNavigation.selectedItemId = savedState
        } else {
            favouriteCityWeatherViewModel.allCitiesWeather.observe(this, Observer {
                if (it.isNotEmpty()) {
                    setUpFavouriteFragment()
                } else {
                    setUpSearchFragment()
                }
            })
        }

        Log.i("CHRIS", "TEST")
    }

    override fun onResume() {
        super.onResume()
        binding.bottomNavigation.selectedItemId = savedState
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        savedState = binding.bottomNavigation.selectedItemId
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
        binding.bottomNavigation.setOnNavigationItemSelectedListener { item ->
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