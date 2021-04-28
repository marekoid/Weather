package com.example.weather.view

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class ItemViewData(val day: String, val time: String, val feelsLikeTemperature: Int)

@Parcelize
data class DetailsViewData(val wind: String): Parcelable