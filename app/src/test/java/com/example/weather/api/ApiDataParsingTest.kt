package com.example.weather.api

import assertk.assertThat
import assertk.assertions.hasSize
import assertk.assertions.isEqualTo
import assertk.assertions.isNotNull
import assertk.assertions.prop
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import org.junit.Test

class ApiDataParsingTest {

    val adapter: JsonAdapter<Response> = Moshi.Builder().build().adapter(Response::class.java)

    @Test
    fun list() {
        val response = adapter.fromJson(jsonResponse)

        assertThat(response).isNotNull().prop(Response::list).hasSize(1)
    }

    @Test
    fun dateTime() {
        val response = adapter.fromJson(jsonResponse)

        assertThat(response!!.list[0].dt).isEqualTo(dateTime)
    }

    @Test
    fun feelsLike() {
        val response = adapter.fromJson(jsonResponse)

        assertThat(response!!.list[0].main.feels_like).isEqualTo(feelsLike)
    }

    @Test
    fun wind() {
        val response = adapter.fromJson(jsonResponse)

        val wind = response!!.list[0].wind
        assertThat(wind.speed).isEqualTo(windSpeed)
        assertThat(wind.gust).isEqualTo(windGust)
    }
}

const val dateTime = 1596564000L
const val feelsLike = 293.13f
const val windSpeed = 4.35f
const val windGust = 7.87f
const val jsonResponse = """
{
  "cod": "200",
  "message": 0,
  "cnt": 40,
  "list": [
    {
      "dt": $dateTime,
      "main": {
        "temp": 293.55,
        "feels_like": $feelsLike,
        "temp_min": 293.55,
        "temp_max": 294.05,
        "pressure": 1013,
        "sea_level": 1013,
        "grnd_level": 976,
        "humidity": 84,
        "temp_kf": -0.5
      },
      "weather": [
        {
          "id": 500,
          "main": "Rain",
          "description": "light rain",
          "icon": "10d"
        }
      ],
      "clouds": {
        "all": 38
      },
      "wind": {
        "speed": $windSpeed,
        "deg": 309,
        "gust": $windGust
      },
      "visibility": 10000,
      "pop": 0.49,
      "rain": {
        "3h": 0.53
      },
      "sys": {
        "pod": "d"
      },
      "dt_txt": "2020-08-04 18:00:00"
    }
  ],
  "city": {
    "id": 2643743,
    "name": "London",
    "coord": {
      "lat": 51.5073,
      "lon": -0.1277
    },
    "country": "GB",
    "timezone": 0,
    "sunrise": 1578384285,
    "sunset": 1578413272
  }
}
"""