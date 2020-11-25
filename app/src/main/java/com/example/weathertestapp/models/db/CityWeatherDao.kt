package com.example.weathertestapp.models.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CityWeatherDao {

    @Query("SELECT * FROM city_weather_table")
    fun getAllWeatherListForCities(): Flow<List<CityWeather>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCityWeather(cityWeather: CityWeather)
}