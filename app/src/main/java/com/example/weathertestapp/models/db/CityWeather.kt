package com.example.weathertestapp.models.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "city_weather_table")
data class CityWeather (
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var name: String,
    var country: String,
    var latitude: Double,
    var longitude: Double,
    var sunrise: Int,
    var sunset: Int,
    var clouds: Int,
    var windInDegrees: Int,
    var windSpeed: Double,
    var favourited: Boolean
)