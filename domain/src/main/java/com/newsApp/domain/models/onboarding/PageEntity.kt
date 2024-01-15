package com.newsApp.domain.models.onboarding

import androidx.annotation.DrawableRes

data class PageEntity(
    val title: String,
    val description: String,
    @DrawableRes val image: Int
)
