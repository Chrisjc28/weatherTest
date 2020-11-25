package com.example.weathertestapp.respositories

import com.example.weathertestapp.models.db.CityWeather
import kotlinx.coroutines.flow.Flow

interface FavouriteWeatherRepository {
    suspend fun insertCityWeather(cityWeather: CityWeather)
    val allCitiesWeather: Flow<List<CityWeather>>
}