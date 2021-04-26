package com.example.weather

import io.reactivex.rxjava3.core.Single

interface Repository {

    fun itemsWithDetails(): Single<List<Pair<Item, Details>>>
}