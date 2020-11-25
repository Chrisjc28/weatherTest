package com.example.weathertestapp.state

import com.example.weathertestapp.models.db.CityWeather

sealed class AppState {
    data class Success(val data: CityWeather? = null) : AppState()
    data class Fail(val error: Throwable? = null) : AppState()
    object Loading : AppState()
}