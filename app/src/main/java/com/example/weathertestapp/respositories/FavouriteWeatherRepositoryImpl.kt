package com.example.weathertestapp.respositories

import com.example.weathertestapp.models.db.CityWeather
import com.example.weathertestapp.models.db.CityWeatherDao
import kotlinx.coroutines.flow.Flow

class FavouriteWeatherRepositoryImpl(
    private val cityWeatherDao: CityWeatherDao
) : FavouriteWeatherRepository {

    override val allCitiesWeather: Flow<List<CityWeather>> = cityWeatherDao.getAllWeatherListForCities()

    override suspend fun insertCityWeather(cityWeather: CityWeather) {
        cityWeatherDao.insertCityWeather(cityWeather)
    }

}