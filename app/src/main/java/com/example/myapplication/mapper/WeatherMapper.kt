package com.example.myapplication.mapper

import com.example.myapplication.domain.model.Weather
import com.example.myapplication.model.response.WeatherResponseDTO

class WeatherMapper {

    fun fromDtoToDomain(weatherResponseDTO: WeatherResponseDTO) = with(weatherResponseDTO){
        weatherResponseDTO.current?.tempC?.let {
            Weather(
                name = weatherResponseDTO.location?.name.toString(),
                temp = it
            )
        }
    }

}