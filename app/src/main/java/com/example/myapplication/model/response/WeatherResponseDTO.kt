package com.example.myapplication.model.response

import com.google.gson.annotations.SerializedName


data class WeatherResponseDTO (

  @SerializedName("location" ) var location : LocationDTO? = LocationDTO(),
  @SerializedName("current"  ) var current  : CurrentDTO?  = CurrentDTO()

)