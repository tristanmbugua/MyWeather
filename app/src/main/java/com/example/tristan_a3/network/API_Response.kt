package com.example.tristan_a3.network

import com.example.tristan_a3.models.Weather;
import retrofit2.http.*;

interface API_Response {
    @GET("current.json")
    suspend fun getWeather(
        @Query("key") key: String,
        @Query("q") location: String
    ): Weather
}