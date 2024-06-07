package com.example.gsaprojectassessment

import retrofit2.Call
import com.example.gsaprojectassessment.model.SearchResult
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {
    @GET("/")
    fun getMovies(
        @Query("type") type: String,
        @Query("apikey") apiKey: String,
        @Query("page") page: Int,
        @Query("s") search: String? = null // Make search query nullable
    ): Call<SearchResult>
}