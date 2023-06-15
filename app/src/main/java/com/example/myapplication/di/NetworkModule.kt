package com.example.myapplication.di

import com.example.myapplication.BuildConfig
import com.example.myapplication.remote.api_service.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): ApiService {
        return Retrofit.Builder().baseUrl(BuildConfig.BASE_URL).client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create()).build().create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideOkHttpClientBuilder(): OkHttpClient.Builder = OkHttpClient.Builder()
        .readTimeout(20, TimeUnit.SECONDS)
        .connectTimeout(20, TimeUnit.SECONDS)

    @Provides
    @Singleton
    fun provideOkHttpClient(
        builder: OkHttpClient.Builder
    ): OkHttpClient {
        builder.addInterceptor(
            Interceptor { chain ->
                val original = chain.request()
                val url = original.url.newBuilder().addQueryParameter("key", BuildConfig.API_KEY).build()
                val requestBuilder = original.newBuilder().url(url)
                chain.proceed(requestBuilder.build())
            },
        )
        return builder.build()
    }

}
