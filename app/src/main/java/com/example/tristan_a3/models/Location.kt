package com.example.tristan_a3.models

import com.squareup.moshi.Json

data class Location(
    @Json(name = "name") val name: String,
    @Json(name = "region") val region: String,
    @Json(name = "country") val country: String,
    @Json(name = "lat") val latitude: Double,
    @Json(name = "lon") val longitude: Double,
    @Json(name = "tz_id") val timezoneId: String,
    @Json(name = "localtime_epoch") val localtimeEpoch: Int,
    @Json(name = "localtime") val localtime: String

) {

}