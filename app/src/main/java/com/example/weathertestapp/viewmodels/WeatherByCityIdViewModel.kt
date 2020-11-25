package com.example.weathertestapp.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weathertestapp.respositories.RawWeatherRepository
import com.example.weathertestapp.state.AppState
import com.example.weathertestapp.utils.CoroutineContextProvider
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

private val LOG_TAG = WeatherByCityIdViewModel::class.java.simpleName

class WeatherByCityIdViewModel(
    private val rawWeatherRepository: RawWeatherRepository,
    private val coroutineContextProvider: CoroutineContextProvider
) : ViewModel() {

    private val _appState = MutableLiveData<AppState>()

    val appState: LiveData<AppState> get() = _appState

    fun fetchWeatherByCityId(cityId: Int) {
        viewModelScope.launch(coroutineContextProvider.io()) {
            rawWeatherRepository.fetchWeatherById(cityId).collect {
                Log.e(LOG_TAG, it.toString())
                _appState.postValue(it)
            }
        }
    }

}