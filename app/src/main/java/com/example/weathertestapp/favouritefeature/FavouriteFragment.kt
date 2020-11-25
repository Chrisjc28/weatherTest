package com.example.weathertestapp.favouritefeature

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.example.weathertestapp.R
import com.example.weathertestapp.adpaters.FavouriteCityWeatherAdapter
import com.example.weathertestapp.viewmodels.FavouriteCityWeatherViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavouriteFragment : Fragment() {

    private val favouriteCityWeatherRecyclerView by lazy {
        requireView().findViewById<RecyclerView>(R.id.favourite_cites)
    }

    private val favouriteCityWeatherViewModel: FavouriteCityWeatherViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.activity_favourtie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        populateFavouritesCitiesWeather()
    }

    private fun populateFavouritesCitiesWeather() {
        favouriteCityWeatherViewModel.allCitiesWeather.observe(
            viewLifecycleOwner,
            Observer { listOfCityWeather ->
                with(favouriteCityWeatherRecyclerView) {
                    adapter = FavouriteCityWeatherAdapter(listOfCityWeather)
                }
            })
    }

    companion object {
        fun newInstance() =
            FavouriteFragment()
    }
}