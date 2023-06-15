package com.example.myapplication.model.response


import com.google.gson.annotations.SerializedName


data class CurrentDTO (
    @SerializedName("temp_c") var tempC  : Double?    = null
)