package com.example.weathertestapp.utils

import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

interface CoroutineContextProvider {
    fun io(): CoroutineContext
    fun computation(): CoroutineContext
    fun ui(): CoroutineContext
}

class CoroutineContextProviderImpl : CoroutineContextProvider {
    override fun computation() = Dispatchers.Default
    override fun ui() = Dispatchers.Main
    override fun io() = Dispatchers.IO
}

