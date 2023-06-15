package com.example.myapplication.repository

import com.example.myapplication.domain.model.Weather
import com.example.myapplication.utils.Resource
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {
    suspend fun getWeather(name : String) : Flow<Resource<Weather>>
}