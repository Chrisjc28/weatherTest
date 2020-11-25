package com.example.weathertestapp.favouritefeature

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.weathertestapp.adpaters.FavouriteCityWeatherAdapter
import com.example.weathertestapp.databinding.FragmentFavourtieBinding
import com.example.weathertestapp.viewmodels.FavouriteCityWeatherViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavouriteFragment : Fragment() {

    private val favouriteCityWeatherViewModel: FavouriteCityWeatherViewModel by viewModel()
    private val binding get() = _binding!!

    private var _binding: FragmentFavourtieBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavourtieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        populateFavouritesCitiesWeather()
    }

    private fun populateFavouritesCitiesWeather() {
        favouriteCityWeatherViewModel.allCitiesWeather.observe(
            viewLifecycleOwner,
            Observer { listOfCityWeather ->
                with(binding.favouriteCites) {
                    adapter = FavouriteCityWeatherAdapter(listOfCityWeather)
                }
            })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance() = FavouriteFragment()
    }
}