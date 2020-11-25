package com.example.weathertestapp.models


import com.example.weathertestapp.models.db.CityWeather
import com.google.gson.annotations.SerializedName

data class RawCityWeather(
    @SerializedName("base")
    val base: String? = null,
    @SerializedName("clouds")
    val clouds: Clouds? = null,
    @SerializedName("cod")
    val cod: Int? = null,
    @SerializedName("coord")
    val coord: Coord? = null,
    @SerializedName("dt")
    val dt: Int? = null,
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("main")
    val main: Main? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("sys")
    val sys: Sys? = null,
    @SerializedName("timezone")
    val timezone: Int? = null,
    @SerializedName("visibility")
    val visibility: Int? = null,
    @SerializedName("weather")
    val weather: List<Weather>? = null,
    @SerializedName("wind")
    val wind: Wind? = null
) {
    fun mapToCityWeather() = CityWeather(
        id = this.id,
        name = this.name,
        country = this.sys?.country,
        latitude = this.coord?.lat,
        longitude = this.coord?.lon,
        sunrise = this.sys?.sunrise,
        sunset = this.sys?.sunset,
        clouds = this.clouds?.all,
        windInDegrees = this.wind?.deg,
        windSpeed = this.wind?.speed,
        temp = this.main?.temp,
        feelsLikeTemp = this.main?.feelsLike,
        maxTemp = this.main?.tempMax,
        minTemp = this.main?.tempMin,
        humidity = this.main?.humidity,
        weatherDescription = this.weather?.first()?.description,
        favourited = false
    )

}