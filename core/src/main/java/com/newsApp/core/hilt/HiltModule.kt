package com.newsApp.core.hilt

import android.app.Application
import com.newsApp.common.APIConstants
import com.newsApp.core.managers.LocalUserManagerImpl
import com.newsApp.core.repositories.newsSource.NewsRepositoryImpl
import com.newsApp.core.repositories.onboarding.OnBoardingRepositoryImpl
import com.newsApp.data.remote.api.NewsApi
import com.newsApp.domain.manager.LocalUserManager
import com.newsApp.domain.repositories.newsScreen.NewsRepository
import com.newsApp.domain.repositories.onboarding.OnBoardingRepository
import com.newsApp.domain.usecases.newsScreen.GetNewsUseCase
import com.newsApp.domain.usecases.onboarding.GetOnBoardingPageListUseCase
import com.newsApp.domain.usecases.onboarding.GetUserAppEntryUseCase
import com.newsApp.domain.usecases.onboarding.SaveUserAppEntryUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
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


    @Provides
    @Singleton
    fun provideApiInterface(): NewsApi {
        val okHttpClient = OkHttpClient.Builder()
//            .addInterceptor(interceptor)
            .build()

        val retrofit = Retrofit.Builder().baseUrl(APIConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
        return retrofit.create(
            NewsApi::class.java,
        )
    }

    @Provides
    @Singleton
    fun providesNewsRepository(newsApi: NewsApi): NewsRepository {
        return NewsRepositoryImpl(newsApi)
    }

    @Provides
    @Singleton
    fun providesGetNewsUseCase(newsRepository: NewsRepository): GetNewsUseCase {
        return GetNewsUseCase(newsRepository)
    }
}