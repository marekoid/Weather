package com.example.weather.view

import android.annotation.SuppressLint
import com.example.weather.Details
import com.example.weather.Item
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@SuppressLint("SimpleDateFormat")
class ViewMapper @Inject constructor() {

    private val dayFormat = SimpleDateFormat("E") // e.g. Tue
    private val timeFormat = SimpleDateFormat("H") // e.g. 9, 17

    fun map(item: Item) =
        with(Date(item.timestamp)) {
            ItemViewData(
                dayFormat.format(this),
                timeFormat.format(this),
                item.feelsLikeTemperature
            )
        }

    fun map(details: Details) = DetailsViewData(
        wind = "Speed: ${details.speed}, gust: ${details.gust}"
    )
}