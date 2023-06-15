package com.example.myapplication.di

import com.example.myapplication.domain.GetWeatherUseCaseImpl
import com.example.myapplication.repository.WeatherRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UserCaseModule {

    @Provides
    @Singleton
    fun providesGetWeatherUseCase(weatherRepository: WeatherRepository): GetWeatherUseCaseImpl =
        GetWeatherUseCaseImpl(weatherRepository = weatherRepository)
}
