package com.example.weathertestapp.models.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "city_weather_table")
data class CityWeather(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    var name: String? = null,
    var country: String? = null,
    var latitude: Double? = null,
    var longitude: Double? = null,
    var sunrise: Long? = null,
    var sunset: Long? = null,
    var clouds: Int? = null,
    var windInDegrees: Int? = null,
    var windSpeed: Double? = null,
    var temp: Double? = null,
    var feelsLikeTemp: Double? = null,
    var maxTemp: Double? = null,
    var minTemp: Double? = null,
    var humidity: Int? = null,
    var weatherDescription: String? = null,
    var favourited: Boolean? = null
)