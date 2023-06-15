package com.example.myapplication.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.domain.GetWeatherUseCaseImpl
import com.example.myapplication.domain.model.Weather
import com.example.myapplication.utils.Constant.DELHI_LOCATION
import com.example.myapplication.utils.Constant.GUJARAT_LOCATION
import com.example.myapplication.utils.Constant.HYDERABAD_LOCATION
import com.example.myapplication.utils.Constant.MUMBAI_LOCATION
import com.example.myapplication.utils.Constant.VISAKHAPATNAM_LOCATION
import com.example.myapplication.utils.Resource
import com.example.myapplication.utils.UiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getWeatherUseCase: GetWeatherUseCaseImpl
) : ViewModel() {

    private val _state = MutableStateFlow<MainViewState>(MainViewState.Init)
    fun getViewState(): StateFlow<MainViewState> = _state.asStateFlow()

    private val weatherList = mutableListOf<Weather>()
    private val errorList = mutableListOf<UiText>()


    fun getWeather() {
        viewModelScope.launch(Dispatchers.IO) {
            weatherList.clear()
            errorList.clear()
            getWeatherUseCase.getWeather(VISAKHAPATNAM_LOCATION)
                .combine(getWeatherUseCase.getWeather(DELHI_LOCATION)) { visakhapatnam, delhi ->
                    when (visakhapatnam) {
                        is Resource.Error -> {
                            errorList.add(visakhapatnam.message)
                        }

                        is Resource.Success -> {
                            visakhapatnam.data?.let {
                                weatherList.add(
                                    Weather(
                                        visakhapatnam.data.name, visakhapatnam.data.temp
                                    )
                                )
                            }
                        }
                    }
                    when (delhi) {
                        is Resource.Error -> {
                            errorList.add(delhi.message)
                        }

                        is Resource.Success -> {
                            delhi.data?.let {
                                weatherList.add(
                                    Weather(
                                        delhi.data.name, delhi.data.temp
                                    )
                                )
                            }
                        }
                    }

                }.combine(getWeatherUseCase.getWeather(HYDERABAD_LOCATION)) { _, hyderabad ->
                    when (hyderabad) {
                        is Resource.Error -> {
                            errorList.add(hyderabad.message)
                        }

                        is Resource.Success -> {
                            hyderabad.data?.let {
                                weatherList.add(
                                    Weather(
                                        hyderabad.data.name, hyderabad.data.temp
                                    )
                                )
                            }
                        }
                    }
                }.combine(getWeatherUseCase.getWeather(GUJARAT_LOCATION)) { _, gujarat ->
                    when (gujarat) {
                        is Resource.Error -> {
                            errorList.add(gujarat.message)
                        }

                        is Resource.Success -> {
                            gujarat.data?.let {
                                weatherList.add(
                                    Weather(
                                        gujarat.data.name, gujarat.data.temp
                                    )
                                )
                            }
                        }
                    }
                }.combine(getWeatherUseCase.getWeather(MUMBAI_LOCATION)) { _, mumbai ->
                    when (mumbai) {
                        is Resource.Error -> {
                            errorList.add(mumbai.message)
                        }

                        is Resource.Success -> {
                            mumbai.data?.let {
                                weatherList.add(
                                    Weather(
                                        mumbai.data.name, mumbai.data.temp
                                    )
                                )
                            }
                        }
                    }
                    if (weatherList.size > 0 && errorList.isEmpty()) _state.value =
                        MainViewState.Success(weatherList)
                    if (weatherList.size == 0 && errorList.isNotEmpty()) _state.value =
                        MainViewState.Error(errorList)
                }
                .collect {}
        }
    }
}

