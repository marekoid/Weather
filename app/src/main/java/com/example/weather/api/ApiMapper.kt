package com.example.weather.api

import com.example.weather.Details
import com.example.weather.Item
import javax.inject.Inject
import kotlin.math.roundToInt

class ApiMapper @Inject constructor() {

    fun map(itemApiData: ItemApiData): Pair<Item, Details> =
        with(itemApiData) {
            Item(dt * 1000, main.feels_like.roundToInt()) to
                    Details(wind.speed.roundToInt(), wind.gust.roundToInt())
        }
}