package com.example.insightfeed.data.remote.api

import com.example.insightfeed.data.remote.dto.news.NewsResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("top-headlines")
    suspend fun getNews(
        @Query("page") page: Int,
        @Query("apiKey") apiKey: String,
        @Query("country") country: String = "us"
    ): NewsResponseDto

    @GET("everything")
    suspend fun searchNews(
        @Query("page") page: Int,
        @Query("q") query: String,
        @Query("apiKey") apiKey: String,
    ): NewsResponseDto
}