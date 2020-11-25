package com.example.weathertestapp.models


import com.google.gson.annotations.SerializedName

data class Sys(
    @SerializedName("country")
    val country: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("sunrise")
    val sunrise: Long,
    @SerializedName("sunset")
    val sunset: Long,
    @SerializedName("type")
    val type: Int
)