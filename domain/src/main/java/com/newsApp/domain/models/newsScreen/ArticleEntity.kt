package com.newsApp.domain.models.newsScreen

data class ArticleEntity(
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    val source: SourceEntity,
    val title: String,
    val url: String,
    val urlToImage: String
)