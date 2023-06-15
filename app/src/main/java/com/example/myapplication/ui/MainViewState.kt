package com.example.myapplication.ui

import com.example.myapplication.domain.model.Weather
import com.example.myapplication.utils.UiText

sealed class MainViewState {
    object Init : MainViewState()
    data class Success(val data: List<Weather>) : MainViewState()
    data class Error(val error: List<UiText>) : MainViewState()
}
