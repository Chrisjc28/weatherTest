package com.example.weathertestapp.searchfeature

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.example.weathertestapp.R
import com.example.weathertestapp.adpaters.CityWeatherAdapter
import com.example.weathertestapp.state.AppState
import com.example.weathertestapp.viewmodels.FavouriteCityWeatherViewModel
import com.example.weathertestapp.viewmodels.WeatherByCityNameViewModel
import com.google.android.material.textfield.TextInputEditText
import org.koin.androidx.viewmodel.ext.android.viewModel

private val LOG_TAG = SearchFragment::class.java.simpleName

class SearchFragment : Fragment() {

    private val searchBtn by lazy {
        requireView().findViewById<Button>(R.id.search_btn)
    }

    private val searchInput by lazy {
        requireView().findViewById<TextInputEditText>(R.id.edit_text_input)
    }

    private val cityWeatherRecyclerView by lazy {
        requireView().findViewById<RecyclerView>(R.id.weather_results)
    }

    private val cityProgressBar by lazy {
        requireView().findViewById<ProgressBar>(R.id.city_progress_bar)
    }

    private val weatherByCityNameViewModel: WeatherByCityNameViewModel by viewModel()

    private val favouriteCityWeatherViewModel: FavouriteCityWeatherViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_search, container, false)

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
        searchBtn.setOnClickListener {
            weatherByCityNameViewModel.fetchWeatherByCity(searchInput.text.toString())
        }
    }

    private fun populateWeather() {
        weatherByCityNameViewModel.appState.observe(viewLifecycleOwner, Observer { appState ->
            when (appState) {
                is AppState.Loading -> {
                    cityProgressBar.visibility = View.VISIBLE
                    cityWeatherRecyclerView.visibility = View.GONE
                    Log.i(LOG_TAG, "Loading")
                }
                is AppState.Fail -> {
                    showErrorDialog(requireContext())
                    Log.e(LOG_TAG, appState.error?.localizedMessage ?: "")
                }
                is AppState.Success -> {
                    cityProgressBar.visibility = View.GONE
                    cityWeatherRecyclerView.visibility = View.VISIBLE
                    with(cityWeatherRecyclerView) {
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