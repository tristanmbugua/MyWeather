package com.example.tristan_a3.models

import com.squareup.moshi.Json

data class Response(
    @Json(name = "location") val location: Location,
    @Json(name = "current") val weather: Weather
) {
}