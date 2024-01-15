package com.newsApp.core.hilt

import com.newsApp.core.repositories.onboarding.OnBoardingRepositoryImpl
import com.newsApp.domain.repositories.onboarding.OnBoardingRepository
import com.newsApp.domain.usecases.onboarding.GetOnBoardingPageListUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object hiltModule {

    @Provides
    @Singleton
    fun providesOnBoardingRepository(): OnBoardingRepository {
        return OnBoardingRepositoryImpl()
    }

    @Provides
    @Singleton
    fun providesGetOnBoardingPageListUseCase(onBoardingRepository: OnBoardingRepository): GetOnBoardingPageListUseCase {
        return GetOnBoardingPageListUseCase(onBoardingRepository)
    }
}