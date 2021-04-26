package com.example.weather.api

import com.example.weather.Details
import com.example.weather.Item
import kotlin.math.roundToInt

class ApiMapper {

    fun map(itemApiData: ItemApiData): Pair<Item, Details> =
        with(itemApiData) {
            Item(dt, main.feels_like.roundToInt()) to
                    Details(wind.speed.roundToInt(), wind.gust.roundToInt())
        }
}