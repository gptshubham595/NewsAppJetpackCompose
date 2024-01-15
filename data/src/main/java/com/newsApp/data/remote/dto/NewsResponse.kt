package com.newsApp.data.remote.dto

data class NewsResponse(
    val articles: List<ArticleData>,
    val status: String,
    val totalResults: Int
)