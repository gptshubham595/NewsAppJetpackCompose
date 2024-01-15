package com.newsApp.data.remote.dto

data class ArticleData(
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    val source: SourceData,
    val title: String,
    val url: String,
    val urlToImage: String
)