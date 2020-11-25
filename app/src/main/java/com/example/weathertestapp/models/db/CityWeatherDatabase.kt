package com.example.weathertestapp.models.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [CityWeather::class], version = 1, exportSchema = false)
abstract class CityWeatherDatabase : RoomDatabase() {

    abstract fun cityWeatherDao(): CityWeatherDao

    companion object {
        @Volatile
        private var INSTANCE: CityWeatherDatabase? = null
        fun getDatabase(context: Context): CityWeatherDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CityWeatherDatabase::class.java,
                    "city_weather_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}