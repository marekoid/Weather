package com.example.weather.api

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface ApiService {

    @GET("2.5/forecast/hourly?q=London&appid=TODO")
    fun fetchHourlyWeather(): Single<Response>
}