package com.example.myapplication.model

import com.example.myapplication.domain.model.ErrorModel


data class ErrorDto(val error: String?)

fun ErrorDto.toErrorModel(): ErrorModel = ErrorModel(error = error)
