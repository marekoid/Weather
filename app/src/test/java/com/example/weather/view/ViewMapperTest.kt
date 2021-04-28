package com.example.weather.view

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.example.weather.Details
import com.example.weather.Item
import org.junit.Test

import java.util.*

class ViewMapperTest {

    val subject = ViewMapper()

    @Test
    fun item() {
        val item = Item(
            GregorianCalendar(TimeZone.getTimeZone("UTC-0")).apply {
                set(2021, Calendar.APRIL, 27, 15, 0)
            }.timeInMillis,
            feelsLikeTemperature = 21
        )

        val itemViewData = subject.map(item)

        assertThat(itemViewData).isEqualTo(ItemViewData("Tue", "16" /* DST */, 21))
    }

    @Test
    fun details() {
        val details = Details(speed = 19, gust = 21)

        val detailsViewData = subject.map(details)

        assertThat(detailsViewData).isEqualTo(DetailsViewData("Speed: 19, gust: 21"))
    }
}