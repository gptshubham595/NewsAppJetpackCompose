package com.newsApp.core.transformer

import com.newsApp.data.onboarding.PageData
import com.newsApp.domain.models.onboarding.PageEntity

fun PageData.toDomain() = PageEntity(
    title = title,
    description = description,
    image = image
)

fun List<PageData>.toDomain() = map { it.toDomain() }