package com.example.myapplication.domain

import com.example.myapplication.domain.model.Weather
import com.example.myapplication.repository.WeatherRepository
import com.example.myapplication.utils.Resource
import kotlinx.coroutines.flow.Flow

class GetWeatherUseCaseImpl(
    private val weatherRepository: WeatherRepository
) {
    suspend fun getWeather(name: String): Flow<Resource<Weather>> =
        weatherRepository.getWeather(name)
}
