package com.example.weather.api

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Response(val list: List<ItemApiData>)

@JsonClass(generateAdapter = true)
data class ItemApiData(
        val dt: Long,
        val main: ItemMainApiData,
        val wind: ItemWindApiData
)

@JsonClass(generateAdapter = true)
data class ItemMainApiData(val feels_like: Float)

@JsonClass(generateAdapter = true)
data class ItemWindApiData(val speed: Float, val gust: Float)
