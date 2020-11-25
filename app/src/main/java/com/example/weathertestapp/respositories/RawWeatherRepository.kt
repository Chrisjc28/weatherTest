package com.example.weathertestapp.respositories

import com.example.weathertestapp.state.AppState
import kotlinx.coroutines.flow.Flow

interface RawWeatherRepository {

    fun fetchWeatherByCity(city: String): Flow<AppState>
    fun fetchWeatherById(cityId: Int): Flow<AppState>
}