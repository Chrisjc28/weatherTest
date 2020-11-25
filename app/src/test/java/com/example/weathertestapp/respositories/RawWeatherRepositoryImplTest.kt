package com.example.weathertestapp.respositories

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.weathertestapp.TestContextProvider
import com.example.weathertestapp.models.RawCityWeather
import com.example.weathertestapp.services.WeatherService
import com.example.weathertestapp.state.AppState
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.doThrow
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.stub
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

class RawWeatherRepositoryImplTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @Test
    fun `fetching weather by city from WeatherRepository returns an app state of loading and success`() {
        val testContextProvider = TestContextProvider()
        val weatherService = mock<WeatherService>()
        val rawCityWeather = RawCityWeather()

        testContextProvider.testCoroutineDispatcher.runBlockingTest {

            weatherService.stub {
                onBlocking { fetchWeatherByCity("Leeds", "fcf3d44da234c38dfac817a42980dc4e") } doReturn rawCityWeather
            }

            val weatherRepository = RawWeatherRepositoryImpl(
                weatherService,
                testContextProvider
            )

            val cityWeatherFlow: List<AppState> = weatherRepository.fetchWeatherByCity("Leeds").toList()

            assertEquals(
                listOf(AppState.Loading, AppState.Success(data = rawCityWeather.mapToCityWeather())),
                cityWeatherFlow
            )
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `fetching weather by city from WeatherRepository and the api returns null an array list type of loading and fail is passed back`() {
        val testContextProvider = TestContextProvider()
        val weatherService = mock<WeatherService>()

        testContextProvider.testCoroutineDispatcher.runBlockingTest {

            weatherService.stub {
                onBlocking { fetchWeatherByCity("Leeds", "fcf3d44da234c38dfac817a42980dc4e") } doThrow (NullPointerException::class)
            }

            val weatherRepository = RawWeatherRepositoryImpl(
                weatherService,
                testContextProvider
            )

            val cityWeatherFlow: List<AppState> = weatherRepository.fetchWeatherByCity("Leeds").toList()

            MatcherAssert.assertThat(
                arrayListOf(AppState.Loading, AppState.Fail(NullPointerException())),
                CoreMatchers.instanceOf<Any>(cityWeatherFlow::class.java)
            )
        }
    }
}