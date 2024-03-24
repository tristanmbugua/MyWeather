package com.example.tristan_a3.network

import com.example.tristan_a3.models.Response
import com.example.tristan_a3.models.Weather;
import retrofit2.Call
import retrofit2.http.*;

interface API_Response {
    @GET("current.json")
    suspend fun getWeather(
        @Query("key") key: String,
        @Query("q") q: String
    ): Response
}