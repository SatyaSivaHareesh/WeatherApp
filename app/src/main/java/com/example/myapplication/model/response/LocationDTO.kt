package com.example.myapplication.model.response

import com.google.gson.annotations.SerializedName


data class LocationDTO (

  @SerializedName("name") var name : String? = null,

)