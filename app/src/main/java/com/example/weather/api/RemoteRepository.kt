package com.example.weather.api

import com.example.weather.Details
import com.example.weather.Item
import com.example.weather.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@Module
@InstallIn(SingletonComponent::class)
class RemoteRepositoryModule {

    @Provides
    fun remoteRepository(apiService: ApiService, mapper: ApiMapper): Repository =
        RemoteRepository(apiService, mapper)
}

private class RemoteRepository @Inject constructor(
    private val apiService: ApiService,
    private val mapper: ApiMapper
) : Repository {

    override fun itemsWithDetails(): Single<List<Pair<Item, Details>>> =
        apiService.fetchHourlyWeather()
            .subscribeOn(Schedulers.io())
            .map { it.list.map(mapper::map) }
}