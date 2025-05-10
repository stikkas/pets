package com.example.pets.data.network

import com.example.pets.data.model.Cat
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CatsApi {
    @GET("cats")
    suspend fun fetchCats(@Query("tag") tag: String): Response<List<Cat>>
}