package com.newsApp.core.transformer

import com.newsApp.data.remote.dto.ArticleData
import com.newsApp.data.remote.dto.SourceData
import com.newsApp.domain.models.newsScreen.ArticleEntity
import com.newsApp.domain.models.newsScreen.SourceEntity

fun ArticleData.toDomain() = ArticleEntity(
    author = author,
    content = content,
    description = description,
    publishedAt = publishedAt,
    source = source.toDomain(),
    title = title,
    url = url,
    urlToImage = urlToImage
)

fun SourceData.toDomain() = SourceEntity(
    id = id,
    name = name
)

fun List<ArticleData>.toDomain() = map { it.toDomain() }