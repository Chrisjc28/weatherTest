package com.example.weathertestapp.utils

import java.text.SimpleDateFormat
import java.util.*

private const val SECONDS_CONVERSION_NUMBER = 1000

object DateUtils {

    fun convertSecondsInToADate(time: Long?): String? {
        return if (time != null) {
            val date = Date(time * SECONDS_CONVERSION_NUMBER)
            val format = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.UK)
            format.format(date)
        } else {
            null
        }
    }
}