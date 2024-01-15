package com.newsApp.core.hilt

import android.app.Application
import com.newsApp.core.managers.LocalUserManagerImpl
import com.newsApp.core.repositories.onboarding.OnBoardingRepositoryImpl
import com.newsApp.domain.manager.LocalUserManager
import com.newsApp.domain.repositories.onboarding.OnBoardingRepository
import com.newsApp.domain.usecases.onboarding.GetOnBoardingPageListUseCase
import com.newsApp.domain.usecases.onboarding.GetUserAppEntryUseCase
import com.newsApp.domain.usecases.onboarding.SaveUserAppEntryUseCase
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

    @Provides
    @Singleton
    fun providesLocalUserManager(application: Application): LocalUserManager {
        return LocalUserManagerImpl(application)
    }

    @Provides
    @Singleton
    fun providesGetUserAppEntryUseCase(localUserManager: LocalUserManager): GetUserAppEntryUseCase {
        return GetUserAppEntryUseCase(localUserManager)
    }

    @Provides
    @Singleton
    fun providesSaveUserAppEntryUseCase(localUserManager: LocalUserManager): SaveUserAppEntryUseCase {
        return SaveUserAppEntryUseCase(localUserManager)
    }
}