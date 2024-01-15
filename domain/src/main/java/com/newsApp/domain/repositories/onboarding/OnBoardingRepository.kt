package com.newsApp.domain.repositories.onboarding

import com.newsApp.domain.models.onboarding.PageEntity
import kotlinx.coroutines.flow.Flow

interface OnBoardingRepository {
    fun getOnBoardingData(): Flow<List<PageEntity>>
}