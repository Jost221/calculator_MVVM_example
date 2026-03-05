package com.example.calculator.data.remote

import com.example.calculator.data.remote.ResponseData.CountryResponse
import retrofit2.http.GET
import retrofit2.http.Headers

interface ApiService {
    @Headers("User-Agent: my-calculator-app")
    @GET("json/")
    suspend fun getCountry(): CountryResponse
}
