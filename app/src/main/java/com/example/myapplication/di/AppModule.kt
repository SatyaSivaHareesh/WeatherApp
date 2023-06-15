package com.example.myapplication.di

import com.example.myapplication.mapper.WeatherMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideWeatherMapper(): WeatherMapper = WeatherMapper()

}