package com.example.weathertestapp.respositories

import com.example.weathertestapp.services.WeatherService
import com.example.weathertestapp.state.AppState
import com.example.weathertestapp.utils.CoroutineContextProvider
import kotlinx.coroutines.flow.*

private val LOG_TAG = RawWeatherRepository::class.java.simpleName

class RawWeatherRepositoryImpl(
    private val weatherService: WeatherService,
    private val coroutineContextProvider: CoroutineContextProvider
) : RawWeatherRepository {

    private val appId = "fcf3d44da234c38dfac817a42980dc4e"

    override fun fetchWeatherByCity(city: String): Flow<AppState> {
        return flow {
            emit(AppState.Loading)
            val cityWeather = weatherService.fetchWeatherByCity(city, appId)
            emit(AppState.Success(cityWeather.mapToCityWeather()))
        }.catch { e ->
            emit(AppState.Fail(e))
        }.flowOn(coroutineContextProvider.io())
    }

    override fun fetchWeatherById(cityId: Int): Flow<AppState> {
        return flow {
            try {
                val cityWeather = weatherService.fetchWeatherById(cityId, appId)
                emit(AppState.Success(cityWeather.mapToCityWeather()))
            } catch (e: Exception) {
                emit(AppState.Fail(e))
            }
        }.flowOn(coroutineContextProvider.io())
    }
}