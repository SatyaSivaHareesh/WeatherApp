package com.example.myapplication.repository


import com.example.myapplication.R
import com.example.myapplication.common.extension.handleError
import com.example.myapplication.domain.model.Weather
import com.example.myapplication.mapper.WeatherMapper
import com.example.myapplication.remote.api_service.ApiService
import com.example.myapplication.utils.Resource
import com.example.myapplication.utils.UiText
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val api: ApiService,
    private val mapper: WeatherMapper
) : WeatherRepository {
    override suspend fun getWeather(name: String): Flow<Resource<Weather>> = flow {
        try {
            val response = api.getWeather(name = name)
            emit(Resource.Success(data = mapper.fromDtoToDomain(response)))
        } catch (e: HttpException) {
            emit(Resource.Error(e.handleError()))
        } catch (e: IOException) {
            emit(Resource.Error(UiText.StringResource(R.string.couldntReachServerError)))
        }
    }
}
