package com.example.myapplication.remote.api_service


import com.example.myapplication.model.response.WeatherResponseDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("v1/current.json?")
    suspend fun getWeather(@Query("q") name : String) : WeatherResponseDTO
}