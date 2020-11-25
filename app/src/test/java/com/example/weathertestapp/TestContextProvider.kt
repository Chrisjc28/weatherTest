package com.example.weathertestapp

import com.example.weathertestapp.utils.CoroutineContextProvider
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher

@ExperimentalCoroutinesApi
class TestContextProvider : CoroutineContextProvider {

    val testCoroutineDispatcher = TestCoroutineDispatcher()

    override fun computation() = testCoroutineDispatcher
    override fun ui() = testCoroutineDispatcher
    override fun io() = testCoroutineDispatcher
}