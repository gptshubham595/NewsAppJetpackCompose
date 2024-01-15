package com.newsApp.data.onboarding

import androidx.annotation.DrawableRes
import com.newsApp.data.R

data class PageData(
    val title: String,
    val description: String,
    @DrawableRes val image: Int
)

val onBoardingPageListData = listOf(
    PageData(
        title = "Lorem Ipsum is simply dummy",
        description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
        image = R.drawable.onboarding1
    ),
    PageData(
        title = "Lorem Ipsum is simply dummy",
        description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
        image = R.drawable.onboarding2
    ),
    PageData(
        title = "Lorem Ipsum is simply dummy",
        description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
        image = R.drawable.onboarding3
    )
)