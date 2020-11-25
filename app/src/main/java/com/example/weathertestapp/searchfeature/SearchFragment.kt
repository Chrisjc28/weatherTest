package com.example.weathertestapp.searchfeature

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.weathertestapp.R
import com.example.weathertestapp.adpaters.CityWeatherAdapter
import com.example.weathertestapp.databinding.FragmentSearchBinding
import com.example.weathertestapp.state.AppState
import com.example.weathertestapp.viewmodels.FavouriteCityWeatherViewModel
import com.example.weathertestapp.viewmodels.WeatherByCityNameViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

private val LOG_TAG = SearchFragment::class.java.simpleName

class SearchFragment : Fragment() {

    private val weatherByCityNameViewModel: WeatherByCityNameViewModel by viewModel()
    private val favouriteCityWeatherViewModel: FavouriteCityWeatherViewModel by viewModel()
    private val binding get() = _binding!!

    private var _binding: FragmentSearchBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpSearchBtn()
        populateWeather()
    }

    companion object {
        fun newInstance() =
            SearchFragment()
    }

    private fun setUpSearchBtn() {
        binding.searchBtn.setOnClickListener {
            weatherByCityNameViewModel.fetchWeatherByCity(binding.searchEditTextInput.text.toString())
        }
    }

    private fun populateWeather() {
        weatherByCityNameViewModel.appState.observe(viewLifecycleOwner, Observer { appState ->
            when (appState) {
                is AppState.Loading -> {
                    binding.cityProgressBar.visibility = View.VISIBLE
                    binding.cityWeatherRecyclerView.visibility = View.GONE
                    Log.i(LOG_TAG, "Loading")
                }
                is AppState.Fail -> {
                    showErrorDialog(requireContext())
                    Log.e(LOG_TAG, appState.error?.localizedMessage ?: "")
                }
                is AppState.Success -> {
                    binding.cityProgressBar.visibility = View.GONE
                    binding.cityWeatherRecyclerView.visibility = View.VISIBLE
                    with(binding.cityWeatherRecyclerView) {
                        adapter = appState.data?.let {
                            CityWeatherAdapter(
                                listOf(it),
                                favouriteCityWeatherViewModel
                            )
                        }
                    }
                }
            }
        })
    }

    private fun showErrorDialog(context: Context): AlertDialog? {
        return AlertDialog.Builder(context)
            .setTitle("Error fetching weather information")
            .setMessage("It seems you have entered an incorrect city, please try again") // Specifying a listener allows you to take an action before dismissing the dialog.
            .setNegativeButton("Close", null)
            .setIcon(R.drawable.ic_weather_dialog_error)
            .show()
    }
}