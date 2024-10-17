package com.mobile.newsbuzz.data.network

import com.mobile.newsbuzz.data.BuildConfig
import com.mobile.newsbuzz.data.network.dto.NewsResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Retrofit interface for [News API] (https://newsapi.org/)
 */
interface NewsApi {
    @GET("top-headlines")
    suspend fun fetchNews(
        @Query("country") country: String?,
        @Query("pageSize") pageSize: Int,
        @Query("page") page: Int,
        @Query("apiKey") apiKey: String = BuildConfig.API_KEY
    ): NewsResponseDto
}
