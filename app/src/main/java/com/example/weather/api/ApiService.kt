package com.example.weather.api

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.reactivex.rxjava3.core.Single
import retrofit2.Retrofit
import retrofit2.http.GET

interface ApiService {

    @GET("2.5/forecast/hourly?q=London&appid=TODO")
    fun fetchHourlyWeather(): Single<Response>
}

@Module
@InstallIn(SingletonComponent::class)
class RetrofitModule {

    @Provides
    fun apiService(): ApiService = Retrofit.Builder()
        .baseUrl("https://pro.openweathermap.org/data/2.5")
        .build()
        .create(ApiService::class.java)
}