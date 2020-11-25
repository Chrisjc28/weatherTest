package com.example.weathertestapp.models.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [CityWeather::class], version = 1, exportSchema = false)
abstract class CityWeatherDatabase : RoomDatabase() {

    abstract fun cityWeatherDao(): CityWeatherDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: CityWeatherDatabase? = null

        fun getDatabase(context: Context): CityWeatherDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CityWeatherDatabase::class.java,
                    "city_weather_database"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}