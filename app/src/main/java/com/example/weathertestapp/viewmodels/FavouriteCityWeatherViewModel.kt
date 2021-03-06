package com.example.weathertestapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.weathertestapp.models.db.CityWeather
import com.example.weathertestapp.respositories.FavouriteWeatherRepository
import kotlinx.coroutines.launch

class FavouriteCityWeatherViewModel(private val favouriteWeatherRepository: FavouriteWeatherRepository) :
    ViewModel() {

    val allCitiesWeather: LiveData<List<CityWeather>> =
        favouriteWeatherRepository.allCitiesWeather.asLiveData()

    fun getCityById(id: Int): LiveData<CityWeather> =
        favouriteWeatherRepository.getCityById(id).asLiveData()

    fun insert(cityWeather: CityWeather) = viewModelScope.launch {
        favouriteWeatherRepository.insertCityWeather(cityWeather)
    }
}