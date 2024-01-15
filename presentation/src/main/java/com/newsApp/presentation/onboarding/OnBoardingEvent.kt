package com.newsApp.presentation.onboarding

sealed class OnBoardingEvent {
    data class SaveAppEntry(val token: String) : OnBoardingEvent()
    object GetAppEntry : OnBoardingEvent()
}