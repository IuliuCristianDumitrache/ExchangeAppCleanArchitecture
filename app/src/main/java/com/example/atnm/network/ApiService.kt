package com.example.atnm.network

import com.example.atnm.models.RatesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface ApiService {

    @Headers("Content-Type: application/json")
    @GET("/rates2.json")
    suspend fun getRates(): Response<RatesResponse>
}