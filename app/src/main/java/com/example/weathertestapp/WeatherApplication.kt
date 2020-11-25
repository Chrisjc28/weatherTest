package com.example.weathertestapp

import android.app.Application
import com.example.weathertestapp.models.db.CityWeatherDatabase
import com.example.weathertestapp.respositories.FavouriteWeatherRepositoryImpl

class WeatherApplication : Application() {

    private val database by lazy { CityWeatherDatabase.getDatabase(this) }
    val repository by lazy { FavouriteWeatherRepositoryImpl(database.cityWeatherDao()) }

}