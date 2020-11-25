package com.example.weathertestapp.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.weathertestapp.TestContextProvider
import com.example.weathertestapp.models.RawCityWeather
import com.example.weathertestapp.respositories.RawWeatherRepository
import com.example.weathertestapp.state.AppState
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class WeatherByCityNameViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @Test
    fun `fetching city weather from the repository returns a flow of successful App state to observe`() {
        val testContextProvider = TestContextProvider()
        val cityWeather = RawCityWeather()
        val appState = AppState.Success(cityWeather)

        testContextProvider.testCoroutineDispatcher.runBlockingTest {
            val rawWeatherRepository: RawWeatherRepository = mock()

            whenever(rawWeatherRepository.fetchWeatherByCity("Leeds")).thenReturn(flowOf(appState))

            val viewModel =
                WeatherByCityNameViewModel(
                    rawWeatherRepository,
                    testContextProvider
                )

            viewModel.fetchWeatherByCity("Leeds")

            assertEquals(
                AppState.Success(cityWeather),
                viewModel.appState.value
            )
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `fetching city weather from the repository returns a flow of failure App state to observe`() {
        val testContextProvider =
            TestContextProvider()

        testContextProvider.testCoroutineDispatcher.runBlockingTest {

            val rawWeatherRepository: RawWeatherRepository = mock()

            whenever(rawWeatherRepository.fetchWeatherByCity("Leeds")).thenReturn(null)

            val viewModel =
                WeatherByCityNameViewModel(
                    rawWeatherRepository,
                    testContextProvider
                )

            viewModel.fetchWeatherByCity("Leeds")

            MatcherAssert.assertThat(
                AppState.Fail(NullPointerException()).error,
                CoreMatchers.instanceOf<Any>(NullPointerException::class.java)
            )


        }
    }
}