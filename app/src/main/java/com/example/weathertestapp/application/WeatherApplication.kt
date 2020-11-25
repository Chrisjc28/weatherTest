package com.example.weathertestapp.application

import android.app.Application
import com.example.weathertestapp.models.db.CityWeatherDatabase
import com.example.weathertestapp.network.Network
import com.example.weathertestapp.respositories.FavouriteWeatherRepository
import com.example.weathertestapp.respositories.FavouriteWeatherRepositoryImpl
import com.example.weathertestapp.respositories.RawWeatherRepository
import com.example.weathertestapp.respositories.RawWeatherRepositoryImpl
import com.example.weathertestapp.services.WeatherService
import com.example.weathertestapp.utils.CoroutineContextProvider
import com.example.weathertestapp.utils.CoroutineContextProviderImpl
import com.example.weathertestapp.viewmodels.FavouriteCityWeatherViewModel
import com.example.weathertestapp.viewmodels.WeatherByCityNameViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class WeatherApplication : Application() {

    private val myModule = module {
        single { CityWeatherDatabase.getDatabase(get()) }
        single { CityWeatherDatabase.getDatabase(get()).cityWeatherDao() }
        single { Network.createService(WeatherService::class.java) }
        single<FavouriteWeatherRepository> { FavouriteWeatherRepositoryImpl(get()) }
        single<RawWeatherRepository> { RawWeatherRepositoryImpl(get(), get()) }
        single<CoroutineContextProvider> { CoroutineContextProviderImpl() }

        viewModel { FavouriteCityWeatherViewModel(get()) }
        viewModel { WeatherByCityNameViewModel(get(), get()) }
    }

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@WeatherApplication)
            modules(myModule)
        }
    }
}