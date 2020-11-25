package com.example.weathertestapp.services

import com.example.weathertestapp.models.RawCityWeather
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {

    @GET("weather")
    suspend fun fetchWeatherByCity(@Query("q") city: String, @Query("appId") appId: String): RawCityWeather

    @GET("weather")
    suspend fun fetchWeatherById(@Query("id") cityId: Int, @Query("appId") appId: String): RawCityWeather

}