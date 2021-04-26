package com.example.weather.api

import com.example.weather.Details
import com.example.weather.Item
import com.example.weather.Repository
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RemoteRepository(
    private val apiService: ApiService,
    private val mapper: ApiMapper
) : Repository {

    override fun itemsWithDetails(): Single<List<Pair<Item, Details>>> =
        apiService.fetchHourlyWeather()
            .subscribeOn(Schedulers.io())
            .map { it.list.map(mapper::map) }
}