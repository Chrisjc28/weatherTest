package com.example.weathertestapp.models.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "city_weather_table")
data class CityWeather (
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    var name: String? = null,
    var country: String? = null,
    var latitude: Double? = null,
    var longitude: Double? = null,
    var sunrise: Int? = null,
    var sunset: Int? = null,
    var clouds: Int? = null,
    var windInDegrees: Int? = null,
    var windSpeed: Double? = null,
    var favourited: Boolean? = null
)