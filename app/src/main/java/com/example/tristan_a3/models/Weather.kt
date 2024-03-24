package com.example.tristan_a3.models

import com.squareup.moshi.Json;
data class Weather (
    @Json(name = "last_updated") val last_updated: String?,
    @Json(name = "temp_c") val temp_c: Double?,
    @Json(name = "temp_f") val temp_f: Double?,
    @Json(name = "is_day") val is_day: Double?,
    @Json(name = "condition") val condition: Object?,
    @Json(name = "wind_mph") val wind_mph: Double?,
    @Json(name = "wind_kph") val wind_kph: Double?,
    @Json(name = "wind_degree") val wind_degree: Double?,
    @Json(name = "wind_dir") val wind_dir: String?,
    @Json(name = "pressure_mb") val pressure_mb: Double?,
    @Json(name = "pressure_in") val pressure_in: Double?,
    @Json(name = "precip_mm") val precip_mm: Double?,
    @Json(name = "precip_in") val precip_in: Double?,
    @Json(name = "humidity") val humidity: Double?,
    @Json(name = "cloud") val cloud: Double?,
    @Json(name = "feelslike_c") val feelslike_c: Double?,
    @Json(name = "feelslike_f") val feelslike_f: Double?,
    @Json(name = "uv") val uv: Double?,
    @Json(name = "air_quality") val air_quality: Object?
) {
    override fun toString(): String {
        return "As of " + last_updated + " it is " + temp_c + " C.";
    }
}