package com.newsApp.data.remote.api

import com.newsApp.common.APIConstants
import com.newsApp.data.remote.dto.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {
    @GET("everything")
    suspend fun getNews(
        @Query("page") query: Int,
        @Query("sources") sources: String,
        @Query("apiKey") apiKey: String = APIConstants.API_KEY
    ): NewsResponse
}