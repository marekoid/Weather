package com.example.weather.api

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.example.weather.Details
import com.example.weather.Item
import org.junit.Test

class ApiMapperTest {

    val subject = ApiMapper()

    @Test
    fun map() {
        val testData = ItemApiData(1L, ItemMainApiData(21.5f), ItemWindApiData(18.49f, 20.99f))

        val result = subject.map(testData)

        assertThat(result).isEqualTo(Item(1L, 22) to Details(18, 21))
    }
}