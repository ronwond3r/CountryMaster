package com.example.countrymaster.networking


import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

// CountryApiService.kt
interface CountryApiService {
    @GET("/v3.1/name/{countryName}")
    suspend fun getCountryInfo(@Path("countryName") countryName: String): Response<CountryInfo>
}

