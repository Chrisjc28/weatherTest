package com.example.weathertestapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weathertestapp.respositories.RawWeatherRepository
import com.example.weathertestapp.state.AppState
import com.example.weathertestapp.utils.CoroutineContextProvider
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

private val LOG_TAG = WeatherByCityNameViewModel::class.java.simpleName

class WeatherByCityNameViewModel(
    private val rawWeatherRepository: RawWeatherRepository,
    private val coroutineContextProvider: CoroutineContextProvider
) : ViewModel() {

    private val _appState = MutableLiveData<AppState>()

    val appState: LiveData<AppState> get() = _appState

    fun fetchWeatherByCity(city: String) {
        viewModelScope.launch(coroutineContextProvider.io()) {
            rawWeatherRepository.fetchWeatherByCity(city).collect {
                _appState.postValue(it)
            }
        }
    }
}