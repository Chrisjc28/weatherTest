package com.example.weathertestapp.respositories

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.weathertestapp.TestContextProvider
import com.example.weathertestapp.models.RawCityWeather
import com.example.weathertestapp.models.db.CityWeather
import com.example.weathertestapp.models.db.CityWeatherDao
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.stub
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class FavouriteWeatherRepositoryImplTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @Test
    fun `fetching list of favourited city weather from FavouriteWeatherRepositoryImpl returns a list Of CityWeather`() {
        val testContextProvider = TestContextProvider()
        val cityWeatherDao = mock<CityWeatherDao>()
        val listOfCityWeather = listOf(RawCityWeather().mapToCityWeather())

        testContextProvider.testCoroutineDispatcher.runBlockingTest {

            cityWeatherDao.stub {
                onBlocking { getAllWeatherListForCities() } doReturn flowOf(listOfCityWeather)
            }

            val weatherRepository = FavouriteWeatherRepositoryImpl(
                cityWeatherDao
            )

            val cityWeatherFlow: Flow<List<CityWeather>> = weatherRepository.allCitiesWeather

            cityWeatherFlow.collect {
                assertEquals(
                    listOfCityWeather,
                    it
                )
            }
        }
    }
}