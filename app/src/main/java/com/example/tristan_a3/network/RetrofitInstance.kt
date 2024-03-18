package com.example.tristan_a3.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitInstance {
    private val baseURL = "https://api.weatherapi.com/v1/"

    private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build();

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi).asLenient())
        .baseUrl(baseURL)
        .build();

    public val retrofitService: API_Response by lazy {
        retrofit.create(API_Response::class.java);
    }
}