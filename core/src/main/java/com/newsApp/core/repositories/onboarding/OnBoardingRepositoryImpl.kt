package com.newsApp.core.repositories.onboarding

import com.newsApp.core.transformer.toDomain
import com.newsApp.data.onboarding.onBoardingPageListData
import com.newsApp.domain.models.onboarding.PageEntity
import com.newsApp.domain.repositories.onboarding.OnBoardingRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Singleton

class OnBoardingRepositoryImpl : OnBoardingRepository {
    override fun getOnBoardingData(): Flow<List<PageEntity>> = flow {
        emit(onBoardingPageListData.toDomain())
    }
}